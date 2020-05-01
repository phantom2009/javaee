/**
 * 业务js
 */
	
//TODO:设置内容区域高度
function setContentAreaHeight(){
	var west=document.getElementById("west");
	var center=document.getElementById("center");
	var visibleArea={
		width:document.documentElement.clientWidth,
		height:document.documentElement.clientHeight
	};
	west.style.height=visibleArea.height-84+"px";
	center.style.height=visibleArea.height-84+"px";	
};

//TODO:设置菜单效果
function setMenuEffect(){			
	var menu=document.getElementById("menu");
	menu.onmouseover=function(evt){
		var evt =evt || window.event;
		var target=evt.target || evt.srcElement;
		if(target.nodeName.toLowerCase() == 'li'){
            target.style.background = "white";
        }
		if(target.nodeName.toLowerCase() == 'a'){
            target.parentNode.style.background = "white";
        }
	};			
	//mouseover事件：不论鼠标指针穿过被选元素或其子元素，都会触发 mouseover 事件。
    //mouseenter事件：只有在鼠标指针穿过被选元素时，才会触发 mouseenter 事件。		
	menu.onmouseout=function(evt){
		var evt =evt || window.event;
		var target=evt.target || evt.srcElement;
		if(target.nodeName.toLowerCase() == 'li'){
            target.style.background = "";
        }
	};			
}