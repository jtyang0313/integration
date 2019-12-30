/*****************************************************************
 
 association   
 
 html代码：  <div class="association" id="acc2"data-data='{"inputName":"itisme","ajaxUrl":"add.thml","callback":"callback","initList":{"code":"1,2,3"},"initId":"1","backcount":10,"outBoxWidth":500}'>
 
 调用：associationFn($("#acc2")); 
 
 data-data='{"inputName":"itisme",	 //input名称，value值 在联级情况以“,”间隔   如：  1,2
 			"ajaxUrl":"add.thml",	//ajaxUrl   参数add.thml?code=1&target='body'&count=5   code    target值：body  返回code本条数据   children 返回code下一级    count 返回数据条数
			"callback":"callback",	//callback  回调函数
			"initList":{"code":"1,2,3"},	//初始列表 code     为空：取一级
			"initId":"1",	//选择框初始值
			"backCount":10,		//返回数据条数     为空：不限
			"outBoxWidth":500}'    //弹出框宽度设置
 
   ajax查询返回值 data=[{"code":1001,"pcode":0,"text":"第一行"}]   pcode 为父级code
*****************************************************************/

function associationFn($a,jsonObj){
	this.$a=$a;
	//console.log($a.attr("data-data"));
	//var jsonObj=JSON.parse($a.attr("data-data"));//jQuery.parseJSON($a.attr("data-data"));
	assInit($a);
	
	$a.find(".assInput").click(function(){
		$a.find(".assOutBox").slideDown(100);	
		assLiWork();
	})
	
	$a.find("input[name='assKeyword']").bind('input propertychange', function() {
		assConAdd( 2 );
	});
	
	function assInit(){
		var liInit="";
		
		$a.append('<div class="assInput"></div>'+
						'<input type="hidden" name="'+jsonObj.inputname+'" value="">'+
						'<div class="assOutBox">'+
							'<input type="text" name="assKeyword" class="form-control" value="" placeholder="关键字">'+
							'<div class="assPrompt">--请点击选择--</div>'+
							'<div class="assCon">'+
								'<ul></ul>'+
							'</div>'+
						'</div>	')
		
		if(jsonObj.outBoxWidth && jsonObj.outBoxWidth > 260) $a.find(".assOutBox").css("width",jsonObj.outBoxWidth+"px")//设置弹框宽度
 		if( jsonObj.initId ){
			$a.find("input[name='"+jsonObj.inputname+"']").val( jsonObj.initId );
			assConAdd( 0 );//初始值
		}
		if( jsonObj.initList ) assConAdd( 1 );//初始列表
		
		liselected();
	}
	
	function assLiWork(){//LI事件添加
		$a.find(".assCon li").off("click").click(function(e){//LI选择
			
			$(this).closest("ul").find("li.yes").removeClass("yes");
			$(this).addClass("yes");
			
			var $thAss=$(this).closest(".association");
			$thAss.find("input[name='assKeyword']").val("");//清除关键字
			
			if($thAss.find(".assPrompt span").length==0){
				$thAss.find(".assPrompt").addClass("yes").html("");
				//$thAss.find("input[type='hidden']").val($(this).attr("data-code"));
				$thAss.find(".assPrompt").append("<span title='"+$(this).text()+"' data-code='"+$(this).attr("data-code")+"' data-pcode='"+$(this).attr("data-pcode")+"'>"+$(this).text()+"</span>");
				$thAss.find(".assPrompt").append("<i>x</i>");
			}else{
				if( $(this).attr("data-pcode")!=$thAss.find(".assPrompt span:last").attr("data-code")){
					$thAss.find(".assPrompt span").remove();
				}
				$thAss.find(".assPrompt").append("<span title='"+$(this).text()+"' data-code='"+$(this).attr("data-code")+"' data-pcode='"+$(this).attr("data-pcode")+"'>"+$(this).text()+"</span>");
			}
			$thAss.find(".assPrompt i").off("click").click(function(e){//联级删除
					
					$(this).closest(".association").find(".assOutBox .assCon ul:gt(0)").remove();
					$(this).closest(".association").find(".assOutBox .assCon ul:eq(0)").show();
					$(this).closest(".assPrompt").find("span").remove();
					$(this).closest(".assPrompt").removeClass("yes").text("--请点击选择--");
					$(this).remove();
					e.stopPropagation();
			})
			$thAss.find(".assPrompt span").off("click").click(function(e){//联级点击
					var assSpanIndex=$(this).index();
					$(this).closest(".assPrompt").find("span:gt("+assSpanIndex+")").remove();
					assConAdd(2);//添加搜索结果
					e.stopPropagation();
			})
			assConAdd(2);
			e.stopPropagation();
		})
	}
	
	function assConAdd( listType ){//添加搜索结果  assConAdd( listType )  0 选择框初始 1 列表初始 2 收搜列表    add.thml?code=1&target='body'&count=5
			//$a.find(".assCon ul:eq(0)").hide();
			//ajax
			var codeStr;
			var conutStr="";
			if( jsonObj.backCount ) conutStr="&count="+jsonObj.backCount;
			var ajaxUrl=jsonObj.ajaxUrl;
			if( listType==0 ){
				ajaxUrl=jsonObj.ajaxUrl+"?code="+jsonObj.initId+"&target='body'"+conutStr;
			}else if( listType==1 ){
				ajaxUrl=jsonObj.ajaxUrl+"?code="+jsonObj.initList+"&target='body'"+conutStr;
			}else{
				ajaxUrl=jsonObj.ajaxUrl+"?code='"+$a.find(".assPrompt span:last").attr("data-code")+"'&target='children'"+conutStr+"&keyword="+$a.find("input[name='assKeyword']").val();
			}
			$.ajax({
				url : ajaxUrl,
				type : 'get',
				error:function(){  
					//layer.alert('查询失败！', {icon: 5,skin: 'layer-ext-moon'})    
				},     
				success:function(data){    
					if( listType==0 ){
						
						data={"code":1001,"pcode":0,"text":"初始值1"}
						$a.find(".assInput").text( data.text );
					}else if( listType==1 ){
						data=[{"code":1001,"pcode":0,"text":"初始列表1"},{"code":1002,"pcode":0,"text":"初始列表2"},{"code":1003,"pcode":0,"text":"初始列表3"},{"code":1004,"pcode":0,"text":"初始列表4"}]
						var strUl="";
						$.each(data,function(i,item){
							strUl=strUl+"<li data-code='"+item.code+"' data-pcode='"+item.pcode+"'>"+item.text+"</li>"
						})
						$a.find(".assOutBox .assCon ul:eq(0)").html(strUl);
					}else{
						if($a.find(".assPrompt span").length<2){
							data=[{"code":1011,"pcode":1001,"text":"加载列表1"},{"code":1012,"pcode":1001,"text":"加载列表2"},{"code":1013,"pcode":1001,"text":"加载列表3"},{"code":1014,"pcode":1001,"text":"加载列表4"}]
						}else{
							data=[];
						}
						var strUl="";
						if(data.length>0){
							
							$.each(data,function(i,item){
								strUl=strUl+"<li data-code='"+item.code+"' data-pcode='"+item.pcode+"'>"+item.text+"</li>"
							})
							if($a.find(".assOutBox .assCon ul").length>1){
								$a.find(".assOutBox .assCon ul:eq(0)").hide();
								$a.find(".assOutBox .assCon ul:eq(1)").html(strUl).show();
							}else{
								$a.find(".assOutBox .assCon").append("<ul>"+strUl+"</ul>")
								$a.find(".assOutBox .assCon ul:eq(0)").hide();
							}
							
						}else{
							$a.find("input[name='"+jsonObj.inputname+"']").val( $a.find(".assPrompt span:last").attr("data-code") );
							var assText="";
							for(var i=0;i<$a.find(".assPrompt span").length;i++){
									assText += $a.find(".assPrompt span:eq("+i+")").text()+">";
							}
							if(assText.length) assText=assText.substring(0,assText.length-1);
							$a.find(".assInput").text( assText );
							if($a.find(".assPrompt span").length<=1){
								$a.find(".assPrompt span").remove();
								$a.find(".assPrompt").removeClass("yes").text("--请点击选择--");
								$a.find(".assOutBox .assCon ul:gt(1)").remove();
								$a.find(".assOutBox .assCon ul:eq(0)").show();	
							}else{
								$a.find(".assPrompt span:last").remove();
							}
							$a.find(".assOutBox").slideUp();
							if(jsonObj.callback.length>0 ) eval(jsonObj.callback)();//点击后调用function
						}
					}
					assLiWork($a)
				}
			});    
	}
	
	function liselected(){
		var liId=$("input[name='"+$a.attr("data-name")+"']").val() || "";
		var liIdarr=[];
		if(liId.length>0){
			liIdarr=liId.split(",");
		}
		for(var i=0;i<liIdarr.length;i++){
			$a.find(".assOutBox .assCon ul li[data-code='"+liIdarr[i]+"']").addClass("yes");
		}
	
	}
		
}
$(document).click(function(e) {
		
		if($(e.target).closest(".association").length == 0){
				//var $(".association .assOutBox:visible").closest(".association");
				if($(".association .assInput").text().length==0){
					$(".association .assPrompt").find("span").remove();
					$(".association .assPrompt").removeClass("yes").text("--请点击选择--");
					$(".association").find(".assOutBox .assCon ul:gt(1)").remove();
					$(".association").find(".assOutBox .assCon ul:eq(0)").show();
				}
				$(".association .assOutBox:visible").hide();
		}else{
				for(var i=0;i<$(".association").length;i++){
					if(!$(e.target).closest(".association").is($(".association:eq("+i+")"))){
						$(".association:eq("+i+")").find(".assPrompt span").remove();
						$(".association:eq("+i+")").find(".assPrompt").removeClass("yes").text("--请点击选择--");
						$(".association:eq("+i+")").find(".assOutBox .assCon ul:gt(1)").remove();
						$(".association:eq("+i+")").find(".assOutBox .assCon ul:eq(0)").show();
						$(".association:eq("+i+") .assOutBox:visible").hide();
					}
				}	
		}
});
