/**
 * 润和（hoperun）2018,拖动。
 */

(function () {
			
	PopWindow.zIndexCounter=1000;
	PopWindow.mask=document.createElement("div");
	PopWindow.mask.id="pop_window_mask";
	
	PopWindow.showMask=function () {
		PopWindow.resizeMask();
		PopWindow.mask.style.zIndex=PopWindow.zIndexCounter++;
		PopWindow.mask.style.display="block";
		
	};
	PopWindow.hideMask=function () {
		PopWindow.mask.style.display="none";
	};
	
	PopWindow.resizeMask=function () {
		var de=document.documentElement;
		de.style.overflow="hidden";
		PopWindow.mask.style.width=de.clientWidth+"px";
		PopWindow.mask.style.height=de.clientHeight+"px";
		de.style.overflow="auto";
	};
	addEvent(window,"resize",function () {
		PopWindow.resizeMask();
	});
	addEvent(window,"load",function () {
		document.body.appendChild(PopWindow.mask);
	});
	
	/**
	args {
		width
		height
		minWidth
		minHeight
		mask				Boolean				是否显示遮罩
		title				HTML 					窗口标题
		content			HTML/Node			窗口内容
		movable 		Boolean				窗口是否可以拖动
		maximizable Boolean				窗口是否可以最大化
		closable		Boolean				窗口是否可以关闭
		resizable		Boolean				窗口是否可以调整大小
		center			Boolean				窗口是否一直居中
	}
	*/
	
	function PopWindow(args) {
		$.initDefaults(PopWindow,this,args);
		
		
		this.node=args.node;                                               //这个参数要求必须
		this.titleBar=$.getNodesByClass("title_bar",this.node)[0];
		this.container=$.getNodesByClass("window_content",this.node)[0];
		this.resizeBtn=$.getNodesByClass("resize_knob",this.node)[0];
		this.maximizeBtn=$.getNodesByClass("maximize_button",this.node)[0];
		this.closeBtn=$.getNodesByClass("close_button",this.node)[0];
		this.content="Content";
		
		this.hide();
		//document.body.appendChild(this.node);
		
		this.setTitle(this.title);
		this.setContent(this.content);
		this.resizeTo(this.width,this.height);
		
		var $this=this;
		
		addEvent(this.node,"mousedown",function () {
			$this.toFront();
		});
		
		if (this.movable) {
			this.DNDInstance=new DND({
				layer:this.node,
				handle:this.titleBar,
				onMove:function(){
					return true;              //TODO:这里又dnd决定为何要这样
				}
			});
		}
		if (this.center) {
			addEvent(window,"resize",function () {			
				$this.moveCenter();
			});
		}
		
		if (!this.closable) {
			this.closeBtn.style.display="none";
		} else {
			addEvent(this.closeBtn,"click",function () {
				$this.hide();
			});
		}
		
		if (!this.maximizable) {
			this.maximizeBtn.style.display="none";
		} else {
			var toggle=function () {
				$this.toggleSize();
			};
			addEvent(this.maximizeBtn,"click",toggle);
			addEvent(this.titleBar,"dblclick",toggle);
		}
		
		if (!this.resizable) {
			this.resizeBtn.style.display="none";
		} else {
			this.resizeDND=new DND({
				layer:this.resizeBtn,
				onMove:function (evt,dnd,pos) {
					var woffset=getOffset($this.node),
							w=$this.resizeBtn.clientWidth,
							h=$this.resizeBtn.clientHeight;
					$this.resizeTo(pos.x-woffset.x+w-2,pos.y-woffset.y+h-2);
					return false;           //TODO:这里又dnd决定为何要这样
				}
			});
		}
		
		
		
	}
	PopWindow.defaults={
		width:"auto",
		height:"auto",
		minWidth:100,
		minHeight:100,
		mask:true,
		title:"Window",
		content:"Content",
		movable:true,
		maximizable:true,
		closable:true,
		resizable:true,
		center:true
	};
	PopWindow.prototype={
		setTitle:function (t) {
			this.titleBar.innerHTML=t;
		},
		resizeTo:function (w,h) {
			if (typeof w=="number") {
				w=Math.max(w,this.minWidth)+"px";
			}
			if (typeof h=="number") {
				h=Math.max(h,this.minHeight)+"px";
			}
			this.node.style.width=w;
			this.node.style.height=h;
		},
		/*setMinSize:function (w,h) {
			this.node.style.minWidth=w;
			this.node.style.minHeight=h;
			var cw=this.node.clientWidth,
					ch=this.node.clientHeight;
					
	
			if (cw && cw<w){
				this.node.style.width=w+"px";
			}
			if (ch && ch<h) {
				this.node.style.height=h+"px";
			}
		},*/
		toggleSize:function () {
			if (this.isMaximize) {
				this.resizeBack();
			} else {
				this.resizeMax();
			}
		},
		resizeBack:function () {
			var s=this.oldSize;
			if (!s) return;
			this.node.style.left=s.x+"px";
			this.node.style.top=s.y+"px";
			this.node.style.width=s.w+"px";
			this.node.style.height=s.h+"px";
			
			this.isMaximize=false;
		},
		resizeMax:function () {
			var de=document.documentElement,
					x=de.scrollLeft,
					y=de.scrollTop,
					w=de.clientWidth-2,
					h=de.clientHeight-2,
					oldOffset=getOffset(this.node),
					ow=this.node.clientWidth,
					oh=this.node.clientHeight;
					
			this.oldSize={
				x:oldOffset.x,
				y:oldOffset.y,
				w:ow,
				h:oh
			};
					
			
					
			this.node.style.left=x+"px";
			this.node.style.top=y+"px";
			this.node.style.width=w+"px";
			this.node.style.height=h+"px";
			
			this.isMaximize=true;
		},
		setContent:function (content) {
			this.container.innerHTML="";
			if (content.nodeType) {
				this.container.appendChild(content);
			} else {
				this.container.innerHTML=content;
			}
		},
		show:function () {
			if (this.mask) {
				PopWindow.showMask();
			}
			this.node.style.display="block";
			this.toFront();
			this.moveCenter();
			this.isVisible=true;
		},
		hide:function () {
			this.node.style.display="none";
			this.isVisible=false;
			if (this.mask) {
				PopWindow.mask.style.display="none";
			}
		},
		toFront:function () {
			this.node.style.zIndex=PopWindow.zIndexCounter++;
		},
		toggleVisible:function () {
			if (this.isVisible) {
				this.hide();
			} else {
				this.show();
			}
		},
		moveCenter:function () {
			var de=document.documentElement;
			this.node.style.left=(de.clientWidth-this.node.offsetWidth)/2+"px";
			this.node.style.top=(de.clientHeight-this.node.offsetHeight)/2+"px";
		}
	};
	
	
	window.PopWindow=PopWindow;
	
})();