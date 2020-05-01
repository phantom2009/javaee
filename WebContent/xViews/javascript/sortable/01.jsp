<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>拖拽排序</title>
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/reset.css">
	<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
	<style type="text/css">
		ul.sortable{
			width: 400px;height: 470px;
			margin: 10px auto;padding-top:0px;
			border: 1px solid #ccc;	overflow: auto;
			overflow-x: hidden;	position: relative;
		}
		ul.sortable li{
			padding: 10px;margin-bottom: 20px;border-top: 1px solid #ccc;
			border-bottom: 1px solid #ccc;cursor: move;
		}
		ul.sortable li:hover{
			background-color: #f6f6f6;
		}
	</style>
</head>
<body>
	<ul id="sortable" class="sortable">
		<li>
			1,JQuery是继prototype之后又一个优秀的Javascript库。它是轻量级的js库
			，它兼容CSS3，还兼容各种浏览器（IE 6.0+, FF 1.5+, Safari 2.0+, Opera
			9.0+），jQuery2.0及后续版本将不再支持IE6/7/8浏览器。
		</li>
		<li>2,jQuery是一个兼容多浏览器的javascript库，核心理念是write less,do
			more(写得更少,做得更多)。jQuery在2006年1月由美国人John
			Resig在纽约的barcamp发布，吸引了来自世界各地的众多JavaScript高手加入
		</li>
		<li>
			3,jQuery是免费、开源的，使用MIT许可协议。jQuery的语法设计可以使开发更加便捷，例如操作文档对象、选择DOM元素、制作动画效果、事件处理、使用Ajax以及其他功能。
		</li>
		<li>
			4,jQuery，顾名思义，也就是JavaScript和查询（Query），即是辅助JavaScript开发的库。
		</li>
		<li>
			5,2006年1月，jQuery的第一个版本面世，至今已经有10年多了（注：这个时间点是截止至出书时间）。虽然过了这么久，但它依然以其简洁、灵活的编程风格让人一见倾心。
		</li>
	</ul>
	<br />
	
	
	<script type="text/javascript">
		/**
		 * ddsort插件
		 */
		(function($){
			$.fn.DDSort=function(opts){
				var $document=$(document);
				var fnEmpty=function(){};
				var settings=$.extend(true,{
					down: fnEmpty,
					move: fnEmpty,
					up: fnEmpty,
					target: 'li',
					cloneStyle: {
						'background-color': '#eee'
					},
					floatStyle: {					
						'position': 'fixed',						
						'box-shadow': '10px 10px 20px 0 #eee',
						'transform': 'rotate(4deg)'
					}
				},opts);
				
				
				return this.each(function(index,element){					
					//console.log(this);   //each()方法内的this指针指向element，他是原生的js dom对象，不是jQuery对象
					//var $this=$(this);     //这的this指向ul，与下面settings.target不同
					
					var that = $( this ),
						height = 'height',
						width = 'width';

					if( that.css( 'box-sizing' ) == 'border-box' ){
						height = 'outerHeight';
						width = 'outerWidth';
					}
					
					that.on("mousedown.DDSort",settings.target,function(evt){										
						var tagName = evt.target.tagName.toLowerCase();    //防止表单元素失效,这样是不允许在表单元素上拖动的
						var $this=$(this);									//这里的this是事件源li 原生dom对象
						if( tagName == 'input' || tagName == 'textarea' || tagName == 'select' ){
							return;
						}
						if(evt.which!=1){     //只允许鼠标左键拖动，右键值为3，中轮键按下是为2
							return ;
						}		
						
						var offset=$this.offset(),     //offset()获取或者设置元素相对于文档的偏移，这就是上面floatStyle使用fixed的原因
							disX = evt.pageX - offset.left,  //pageX/Y获取到的是触发点相对文档区域左上角距离，会随着页面滚动而改变,我们叫“鼠标在页面中的位置”
							disY = evt.pageY - offset.top,   //这种减法获取的是鼠标在目标对象内部相对于目标对象左上角的距离，这个是通过的核心原理这一，也是事件对象比较难以理解的地方之一
							clone = $this.clone()
								.css( settings.cloneStyle )
								.css( 'height', $this[ height ]() )
								.empty(),
							hasClone = 1,
							
							//缓存计算
							thisOuterHeight = $this.outerHeight(),
							thatOuterHeight = that.outerHeight(),

							//滚动速度
							upSpeed = thisOuterHeight,
							downSpeed = thisOuterHeight,
							maxSpeed = thisOuterHeight * 3;
						
						//执行down函数并且将$this作为函数的调用对象，函数内部this执行$this,所有叫对象冒充
						//对象冒充，是继承机制，又类似反射，只有参数才能调用call(obj,object ... args)方法
						settings.down.call($this);   
						
						//继续赋值事件,第二个参数对象不填是因为在document内部移动，这就是为什么floatStyle使用fixed的原因之一
						$document.on("mousemove.DDSort",function(e){
							if(hasClone){   
								$this.before( clone )   //将克隆对象留在原地
								.css( 'width', $this[ width ]() )
								.css( settings.floatStyle )
								.appendTo( $this.parent() );
								hasClone=0;
							}	
							
							var left = e.pageX - disX,
							top = e.pageY - disY;
							
							//拖动的原理原理核心之二,鼠标移动时候，一直重新计算evt.pageX,获取到值后在减去鼠标按下时候在目标对象上的偏移，在将这个结果
							//	作为事件源的新的left top值
							$this.css({
								left: left,
								top: top
							});
							
							var prev = clone.prev(),
							next = clone.next().not( $this );
							
							//向上排序
							if( prev.length && top < prev.offset().top + prev.outerHeight()/2 ){
									
								clone.after( prev );
								
							//向下排序
							}else if( next.length && top + thisOuterHeight > next.offset().top + next.outerHeight()/2 ){
								
								clone.before( next );

							}
							
							/**
							 * 处理滚动条
							 * that是带着滚动条的元素，这里默认以为that元素是这样的元素（正常情况就是这样），如果使用者事件委托的元素不是这样的元素，那么需要提供接口出来
							 */
							var thatScrollTop = that.scrollTop(),
								thatOffsetTop = that.offset().top,
								scrollVal;
							
							//向上滚动
							if( top < thatOffsetTop ){

								downSpeed = thisOuterHeight;
								upSpeed = ++upSpeed > maxSpeed ? maxSpeed : upSpeed;
								scrollVal = thatScrollTop - upSpeed;

							//向下滚动
							}else if( top + thisOuterHeight - thatOffsetTop > thatOuterHeight ){

								upSpeed = thisOuterHeight;
								downSpeed = ++downSpeed > maxSpeed ? maxSpeed : downSpeed;
								scrollVal = thatScrollTop + downSpeed;
							}

							that.scrollTop( scrollVal );
							
							settings.move.call($this);
							
						}).on("mouseup.DDSort",function(evt){
							
							//移除mousemove mouseup事件
							$document.off( 'mousemove.DDSort mouseup.DDSort' );
							
							//click的时候也会触发mouseup事件，加上判断阻止这种情况
							if( !hasClone ){
								clone.before( $this.removeAttr( 'style' ) ).remove();
								settings.up.call($this);
							}
						});
						
						
						
					});
					
					return this;
				});
			};
			
			
			
			
		})(jQuery);
	
		//$(".sortable").DDSort().css("background","red");  //jQuery建议支持此连缀，但作者最初的写法不支持连缀，他在each()方法内部返回false;
		$("#sortable").DDSort();
		
		
	</script>	
</body>
</html>