
;(function($){
	 $.fn.assotab = function(options) { //定义插件的名称，这里为userCp
		if(typeof options =="string"){
			if(options=="clear"){
				$(this).val("");
				let dStr=$(this).attr("data-str") ? $(this).attr("data-str") : ""
				$(this).siblings(".assotab-iboxcon").text(dStr).attr("data-str","");
			}
			return false;
		}
		 var aData = {
			 valField:"id"
		 };
		 var ops = $.extend(aData,options);
		// $(this).val(ops.width); //把版权文字加入到想显示的div
		 var assVal=$(this).val();
		 var _this=$(this);
		 var data=ops.data;
		 var tabName=ops.tabName;
		 //初始
		let assotabInit=function(){
			
			var widthStr="";
			if(ops.width){
				widthStr='width:'+ops.width+'px'
			}
			if(_this.is('input')){
				_this.attr("type","hidden");
				if(assVal.length>0){
					_this.after('<div class="assotab-ibox " style="height:'+_this.outerHeight()+'px;" id="'+_this.attr("id")+'-input"><div class="assotab-iboxcon '+_this.attr("class")+'" data-str="'+assotabValStr()+'">'+assotabValStr()+'</div></div>')
				}else{
					_this.after('<div class="assotab-ibox " style="height:'+_this.outerHeight()+'px;" id="'+_this.attr("id")+'-input"><div class="assotab-iboxcon '+_this.attr("class")+'"></div></div>')
				}
				_this.next(".assotab-ibox").append(_this);

				_this.siblings(".assotab-iboxcon").click(function(){
					if(_this.siblings(".assotab:visible").length>0) return false;
					assVal=_this.val();
					let tabStr="";
					let tabConStr="";
					for(var i=0;i<tabName.length;i++){
						tabStr +='<li>'+tabName[i]+'</li>';		
						tabConStr +='<ul></ul>'
					}
					_this.closest(".assotab-ibox").append('<div class="assotab" style="'+widthStr+'">'+
								'<ul class="asso-tab">'+tabStr+
								'</ul>'+
								'<div class="asso-con">'+tabConStr+'</div>')
					if(assVal.length>0){
						let _assotab=_this.next(".assotab");
						_assotab.find(".asso-tab li").addClass("y");
						_assotab.find(".asso-tab li:last").addClass("active");
						_assotab.find(".asso-con ul:last").addClass("show");
						//添加 con li
						let pcode,code;
						if(ops.valField=="id"){
							pcode=getParentCode(assVal);
							code=getCode(assVal);
						}else{
							pcode=getPCodeByCode(assVal);
							code=assVal;
						}
						
						for(var i=tabName.length-1; i>=0;i--){
							createTabCon(pcode,i);
							_this.siblings(".assotab").find(".asso-con ul").eq(i).find("li[data-code='"+code+"']").addClass("active");
							code=pcode;
							pcode=getPCodeByCode(pcode);
						}
					}else{
						let _assotab=_this.siblings(".assotab");
						_assotab.find(".asso-tab li:eq(0)").addClass("y");
						_assotab.find(".asso-tab li:eq(0)").addClass("active");
						_assotab.find(".asso-con ul:eq(0)").addClass("show");
						createTabCon("0",0)
					}
					tabTopWork();
				})
			}
		}
		//事件生成  选项卡事件
		let tabTopWork=function(){
			_this.siblings(".assotab").find(".asso-tab li").click(function(){
				if(!$(this).hasClass("active") && $(this).hasClass("y")){
					let tIndex=$(this).index();
					$(this).addClass("active").siblings().removeClass("active");
					_this.siblings(".assotab").find(".asso-con ul").eq(tIndex).addClass("show").siblings().removeClass("show");
				}
			})
		}
		//选项卡 内容块 li事件
		let tabConWork=function(){
			for(let i=0;i<tabName.length;i++){
				tabConLiWork(i)
			}
		}
		let tabConLiWork=function(conIndex){
			_conUl=_this.siblings(".assotab").find(".asso-con ul").eq(conIndex)
			for(let i=0;i<_conUl.find("li").length;i++){
				_conUl.find("li").eq(i).click(function(){
					$(this).addClass("active").siblings().removeClass("active");
					let conIndex=$(this).closest("ul").index();
					if(conIndex<tabName.length){
						_this.siblings(".assotab").find(".asso-tab li:gt("+conIndex+")").removeClass("y");
						_this.siblings(".assotab").find(".asso-tab li:eq("+(conIndex+1)+")").addClass("active").addClass("y").siblings().removeClass("active");
						_this.siblings(".assotab").find(".asso-con ul").eq(conIndex+1).addClass("show").siblings().removeClass("show");
						createTabCon($(this).attr("data-code"),conIndex+1);
						if(conIndex==0){
							_this.siblings(".assotab-iboxcon").text($(this).attr("data-name"))
						}else{
							var tStr=""
							for(let j=0; j<=conIndex;j++){
								tStr +=_this.siblings(".assotab").find(".asso-con ul").eq(j).find("li.active").attr("data-name")+" / ";
							}
							tStr=tStr.length > 3 ? tStr.substring(0,tStr.length-3) : "";
							_this.siblings(".assotab-iboxcon").text(tStr);
						}
					}
					if(conIndex==(tabName.length-1)){
						if(ops.valField=="id"){
							_this.attr("value",$(this).attr("data-id"));
						}else{
							_this.attr("value",$(this).attr("data-code"));
						}
						
						_this.siblings(".assotab-iboxcon").attr("data-str",tStr);
						_this.siblings(".assotab").remove();///////////////////////
						if(typeof ops.callback == "string") eval(ops.callback)();
						if(typeof ops.callback == "function") ops.callback(5);
					}
				})
			}
		}
		//添加 con li
		let createTabCon=function(pcode,conIndex){
			_this.siblings(".assotab").find(".asso-con ul").eq(conIndex).find("li").remove();
			for(var j=0;j<data.length;j++){
				if(data[j].parentCode==pcode)
					_this.siblings(".assotab").find(".asso-con ul").eq(conIndex).append('<li class="" data-code="'+data[j].code+'" data-parentcode="'+data[j].parentCode+'" data-name="'+data[j].name+'" data-id="'+data[j].id+'" >'+data[j].name+'</li>')
			}
			tabConLiWork(conIndex);
		}

		//获取parentCode  id
		let getParentCode=function(aid){
			for(var i=0;i<data.length;i++){
				if(data[i].id==aid) return data[i].parentCode;
			}
		}
		let getCode=function(aid){
			for(var i=0;i<data.length;i++){
				if(data[i].id==aid) return data[i].code;
			}
		}
		let getPCodeByCode=function(code){
			for(var i=0;i<data.length;i++){
				if(data[i].code==code) return data[i].parentCode;
			}
		}
		//获取极联文字串
		let assotabValStr=function(){
			try{
				let istr=_this.attr("data-str");
				if(istr.length>0 && assVal.length==0) return istr;
			}catch(e){}
			if(ops.valField=="id"){
				for(var i=0; i<data.length;i++){
					if(data[i].id==assVal) return getLevelStr(data[i].code)
				}
			}else{
				return getLevelStr(assVal)
			}
		}
		//获取极联文字串  递归
		let getLevelStr=function(code,vstr){
			vstr= vstr ? vstr : "";
			for(var i=0; i<data.length;i++){
				if(data[i].code==code){
					vstr=data[i].name+' / '+vstr;
					if(data[i].parentCode!="0"){
						return getLevelStr(data[i].parentCode,vstr)
					}else{
						return vstr.substring(0,vstr.length-2)
					}
				} 
			}
		}
		assotabInit();
	 }
})(jQuery);

$(document).click(function(e) {
		
		if($(e.target).closest(".assotab-ibox").length == 0){
				for(var i=0;i<$(".assotab-ibox").length;i++){
					if($(".assotab-ibox").eq(i).find(".assotab").length>0){
						$(".assotab-ibox").eq(i).find(".assotab-iboxcon").text($(".assotab-ibox").eq(i).find(".assotab-iboxcon").attr("data-str"))
						$(".assotab-ibox").eq(i).find(".assotab").remove();
					}
				}
		}else{
				for(var i=0;i<$(".assotab-ibox").length;i++){
					if(!$(e.target).closest(".assotab-ibox").is($(".assotab-ibox:eq("+i+")"))){
						if($(".assotab-ibox").eq(i).find(".assotab").length>0){
							$(".assotab-ibox").eq(i).find(".assotab-iboxcon").text($(".assotab-ibox").eq(i).find(".assotab-iboxcon").attr("data-str"))
							$(".assotab-ibox").eq(i).find(".assotab").remove();
						}
					}
				}	
		}
});
