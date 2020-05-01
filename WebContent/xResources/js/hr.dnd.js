/**
 * hr.dnd.js,Sky Davis,2018
 */

(function(){
	/*
	 * 现在改写参数，options方式传递参数，这种方式比arguments对象还要通用
	 * layer:必须，dom结构中需要被拖动的层
	 * handle：可选，位于layer中，拖动手柄
	 * range：可选，拖动范围，如果没有传递则为整个文档作为拖动区域，
	 * onMove:可选，function,这种自定义生命周期回调事件的方法非常重要，我们在实例方法中调用生命周期方法并且注入实例方法中的值，
	 * 		由于在实例方法中我们使用该方法的返回值决定是否移动或者真的拖动，因此，注入后如果不改变拖动行为请一定return true.
	 */
	function DND(opts){						
		
		//TODO:初始化默认参数
		$.initDefaults(DND,this,opts);
						                                            
		this.handle=this.handle || this.layer;                                              

		//TODO:生命周期事件不属于默认参数
		this.onStartDrag=opts.onStartDrag;
		this.onMove=opts.onMove;
		this.onStopDrag=opts.onStopDrag;
		
		var _this=this;	
		
		if (this.range.nodeType) {                                       
			var _range=this.range;
			this.range={
				valueOf:function () {
					var offset=getOffset(_range),
							ml=parseInt(getRealStyle(opts.layer,"margin-left")) || 0,          
							mr=parseInt(getRealStyle(opts.layer,"margin-right")) || 0,
							mt=parseInt(getRealStyle(opts.layer,"margin-top")) || 0,
							mb=parseInt(getRealStyle(opts.layer,"margin-bottom")) || 0;
					return {
						minX:offset.x,
						minY:offset.y,
						maxX:offset.x+_range.clientWidth-_this.layer.offsetWidth-ml-mr,
						maxY:offset.y+_range.clientHeight-_this.layer.offsetHeight-mt-mb
					};
				}
			};
		}
		
		//TODO实例化后即允许拖动，如果想要禁止拖动可以在onStartDrag回调函数中做说明
		addEvent(_this.handle,'mousedown',function(evt){			
			_this.startDrag(evt);			                          
		});	
	}
	
	//TODO:默认参数
	DND.defaults={
		range:document.documentElement            //当用户不传递拖动范围的时候，使用整个document作为拖动范围
	};
	
	//TODO:实例方法
	DND.prototype={
		
		startDrag:function(evt){                                        
			var _this=this;
			evt.preventDefault();
			
			//TODO:记忆鼠标按下时候再target上的偏移
			this.offset={                                              
				x:evt.layerX,
				y:evt.layerY
			};
			
			//TODO:保留这两个返回时间的句柄是为了删除事件时候用
			this.mousemoveHandle=function(evt){
				evt.preventDefault();
				_this.move(evt);
			};
			this.mouseupHandle=function(evt){
				evt.preventDefault();
				_this.stopDrag();
			};
			addEvent(document,'mousemove',this.mousemoveHandle);
			addEvent(document,'mouseup',this.mouseupHandle);
			addEvent(window,"blur",this.mouseupHandle);                 
			
			//TODO:分别处理ie默认文本选中和鼠标移到视区外事件捕获的兼容性问题
			if (document.selection && document.selection.empty) {             
				document.selection.empty();                             
			}					
			if (this.layer.setCapture) {                               
				this.layer.setCapture(true);
			}
			
			//TODO:处理回调,如果没有传递该回调函数则下面的代码无视
			var res=true;
			if (this.onDragStart) res=this.onDragStart(evt,this);
			if (res===false) return false;			
		},
		move:function(evt){
			
			//TODO:拖动的本质：鼠标在当前视区的偏移减去鼠标在目标元素上的偏移，将值设为目标元素的坐标即实现                                 
			var x=evt.clientX-this.offset.x;
			var y=evt.clientY-this.offset.y;
			
			var de=document.documentElement;
			var range=this.range.valueOf(this);
			
			x=Math.max(x,range.minX);
			x=Math.min(x,range.maxX);
			y=Math.max(y,range.minY);
			y=Math.min(y,range.maxY);
			
			//TODO:对外提供回调，行为可以进一步解耦，回调函数与注入数据是非常高端的写法
			var res=true;
			if (this.onMove){ 
				res=this.onMove(evt,this,{x:x,y:y});                      
			}			
			if(!res) return false;		
			this.moveTo(x,y);
		},
		moveTo:function(x,y){
			this.layer.style.left=x+"px";
			this.layer.style.top=y+"px";
		},
		stopDrag:function(){
		
			delEvent(document,'mousemove',this.mousemoveHandle);
			delEvent(document,'mouseup',this.mouseupHandle);
			delEvent(window,"blur",this.mouseupHandle);
			
			if (this.layer.releaseCapture) {                            //IE,鼠标从外部移回来之后释放捕获
				this.layer.releaseCapture(true);
			}			
			//TODO:继续处理停止拖动的回调
		}
	};
	window.DND=DND;
})();