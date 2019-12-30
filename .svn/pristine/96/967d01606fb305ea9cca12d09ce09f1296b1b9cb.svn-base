$(function(){
	
	
	//列显示
	if (localStorage["entryTh"+userId]) {
		thShow(localStorage["entryTh"+userId].split(","))
	}
})
	function openTableModal() {
		
		$('#tableModal').modal({
			backdrop: 'static'
		})
		
		if($("#tableModal").attr("data-icheck")=="0"){
			thArr=[];
			if (localStorage["entryTh"+userId]) {
				thArr=localStorage["entryTh"+userId].split(",")
			}
			for(var i=2;i<$("table.table thead th").length-1;i++){
				isChecked="checked"
				if(thArr.length==$("table.table thead th").length-3){
					isChecked=thArr[i-2]=="1" ? "checked" : ""
				}
				$("#tableModal .modal-body .row").append('<div class="col-sm-4">'+
						'<label  class="checkbox i-checks"> <input name="lineIds" type="checkbox" value="'+i+'" '+isChecked+' >'+$("table.table thead th").eq(i).text()+'</label>'+
						'</div>')
			}
			$('#tableModal .modal-body .i-checks').iCheck({
				checkboxClass: 'icheckbox_square-green',
				radioClass: 'iradio_square-green',
			});
			$("#tableModal").attr("data-icheck","1");
		}
	}
	$("#tableModalSubmit").click(function(){
		thArr=[]
		for(var i=0;i<$("#tableModal input[name='lineIds']").length; i++){
			thArr.push($("#tableModal input[name='lineIds']").eq(i).prop("checked") ? 1 : 0)
		}
		$("#tableModal").modal('hide');	 
		thShow(thArr);
		localStorage["entryTh"+userId]=thArr.join(",");
		tBtnLeft();//行 保存 框定位
	})
	function thShow(thArr){
		for(var i=2;i<$("table.table thead th").length-1;i++){
			if(thArr[i-2]==1){
				$("table.table thead th").eq(i).show();
				for(var j=0; j<$("table.table tbody tr").length; j++){
					$("table.table tbody tr").eq(j).find("td:eq("+i+")").show()
				}
			}else{
				$("table.table thead th").eq(i).hide();
				for(var j=0; j<$("table.table tbody tr").length; j++){
					$("table.table tbody tr").eq(j).find("td").eq(i).hide()
				}
			}
		}
	}
	
	//行保存  按钮框 
	$(".navbar-minimalize").click(function(){//左边菜单变化时
		setTimeout(function(){tBtnLeft()},500) 
	})
	$(".table-responsive").scroll(function(){
		tBtnLeft();
	})
	$(window).resize(function(){
		tBtnLeft()
	})
	function tBtnLeft(){
		let tBtnBoxLeft=$(".table-responsive").scrollLeft()
		let tTableWidth=$(".table-responsive").width();
		let scrollBarWidth = $(".table-responsive")[0].offsetWidth - $(".table-responsive")[0].clientWidth;
		$(".table-r .tBtnBox").css("left",(tBtnBoxLeft+tTableWidth-140-scrollBarWidth)+"px");
	}
	tBtnLeft();
	//双击
	$("table.table > tbody > tr").dblclick(function(){
		if($("table.table > tbody > tr.edit").length>0){
			_tr=$("table.table > tbody > tr.edit");
			trCancel(_tr);
			$(this).siblings().removeClass("active");
			$(this).siblings().removeClass("edit");
		}						
		$(this).addClass("active");
		trIndex=$(this).index();
		$(this).addClass("edit");
		$(".table-r .tBtnBox").show().css("top",(41+42*trIndex)+"px");
		
		var lineCode = $(this).find("td:eq(0) input").attr("data-linecode")
		var trainId = $(this).find("td:eq(0) input").attr("data-trainId")
		var select = $(this).find("#trainIdTd select");
		console.info(lineCode)	
		$.ajax({
			dataType:'json',
			type : 'get',
			url:'/entry/doubleClick',
			data:{
				lineCode:lineCode,
				trainId:trainId
			},
			success:function(data)
			{
				select.find("option[value != '']").remove();
				//$("#trainIdL option[value != '']").remove(); //删除Select中value不为0的Option
				if(data != null)//班列
				{
					//<option th:each="m : ${entry.trainList}" th:value="${m.id}" th:data-totalno="${m.totalNo}" th:data-transitName="${m.cheCi != null}?${m.cheCi}:' '" th:text="${m.trainNo}" th:if="${entry.trainId} != ${m.id}"></option>
					$.each(data, function (i, item) {  		            		   							
						select.append("<option value='"+item.id+"' data-totalno='"+item.trainNo+"' data-transitName='"+item.cheCi+"'>"+item.trainNo+"</option>");		                    
	                });
					select.find("option[value='"+trainId+"']").attr("selected",true);
				}		
			}
		});
	})
	//保存
	$(".table-r .tBtnBox .btnEdit").click(function(){
		_tr=$("table.table > tbody > tr.edit");
		var entryid=$("table.table > tbody > tr.edit").attr("data-id");
		entryJson={	
				"id":entryid,
				"trainId": _tr.find("select[name='trainIdL'] option:selected").attr("value"),
				"isTurn": _tr.find("select[name='isTurn'] option:selected").attr("value"),
				"finalDestination": _tr.find("input[name='finalDestination']").val(),
				"transit": _tr.find("select[name='transit'] option:selected").attr("value"),
				"ticketCode":_tr.find("input[name='ticketCode']").val(),
				"ticketDate":_tr.find("input[name='ticketDate']").val(),
				"station":_tr.find("input[name='station']").val(),
				"wagonCode":_tr.find("input[name='wagonCode']").val(),
				"declareStatus": _tr.find("select[name='declareStatus'] option:selected").attr("value"),
				"takeDate":_tr.find("input[name='takeDate']").val(),
				"arriveDate":_tr.find("input[name='arriveDate']").val(),
				"billCode":_tr.find("input[name='billCode']").val(),
				"startTime":_tr.find("input[name='startTime']").val()					
			}
		layerx=layer.msg('保存中', {
			  icon: 16
			  ,shade: 0.01
			});
		$.ajax({
				url : '/entry/save',
				timeout : 600000,
				type : 'post',
				data :{
					entryJson:JSON.stringify(entryJson),
				},

				error:function(){ 
					layer.close(layerx);
					layer.msg('操作失败！', {icon: 2})
				},     
				success:function(data){
					layer.close(layerx);
					if(data){
						layer.msg('已保存', {icon: 1});
						_tr.removeClass("active");
						_tr.removeClass("edit");
						$(".table-r .tBtnBox").hide();
						_tr.find(".trainId").text(_tr.find("select[name='trainIdL'] option:selected").text())
						_tr.find(".totalNo").text(_tr.find("select[name='trainId'] option:selected").attr("data-totalno"))
						_tr.find(".transitName").text(_tr.find("select[name='transit'] option:selected").text())
						_tr.find(".isTurn").text(_tr.find("select[name='isTurn'] option:selected").text())
						_tr.find(".declareStatus").text(_tr.find("select[name='declareStatus'] option:selected").text())
						_tr.find(".finalDestination").text(_tr.find("input[name='finalDestination']").val())
						_tr.find(".ticketCode").text(_tr.find("input[name='ticketCode']").val())
						_tr.find(".ticketDate").text(_tr.find("input[name='ticketDate']").val())
						_tr.find(".transitName").text(_tr.find("input[name='transitName']").val())
						_tr.find(".station").text(_tr.find("input[name='station']").val())
						_tr.find(".wagonCode").text(_tr.find("input[name='wagonCode']").val())
						_tr.find(".declareStatus").text(_tr.find("input[name='declareStatus']").val())
						_tr.find(".takeDate").text(_tr.find("input[name='takeDate']").val())
						_tr.find(".arriveDate").text(_tr.find("input[name='arriveDate']").val())
						_tr.find(".billCode").text(_tr.find("input[name='billCode']").val())
						_tr.find(".startTime").text(_tr.find("input[name='startTime']").val())
					}else{
						layer.msg('修改失败，已发车的进场单无法修改班列号!', {icon: 2,time:3000});
					}
					
				}
		  });
	})
	//取消
	$(".table-r .tBtnBox .btnClear").click(function(){
		_tr=$("table.table > tbody > tr.edit");
		$("table.table > tbody > tr.edit").removeClass("edit");
		$("table.table > tbody > tr.active").removeClass("active");
		$(this).closest(".tBtnBox").hide();
		trCancel(_tr);
		
	})
	function trCancel(_tr){
		var entryid=_tr.attr("data-id");
		$.ajax({
			url : "/entry/cancel",
			type : "post",
			dataType:"json",
			data:{
				"id":entryid
			},
			error:function(){
				layer.open({
					content: '操作失败'
					,skin: 'msg'
					,time: 2000 //2秒后自动关闭
				});
			},
			success:function(data){	
				//json串
				_tr.find("select[name='trainIdL'] option[value='"+data.trainId+"']").prop("selected",true)
				_tr.find("select[name='isTurn'] option[value='"+parseInt(data.isTurn)+"']").prop("selected",true)
				_tr.find("input[name='finalDestination']").val(data.finalDestination)
				//转运地
				_tr.find("input[name='transitName']").val(data.transitName)
				_tr.find("input[name='ticketCode']").val(data.ticketCode)
				_tr.find("input[name='ticketDate']").val(data.ticketDate)
				_tr.find("input[name='station']").val(data.station)
				_tr.find("input[name='wagonCode']").val(data.wagonCode)
				_tr.find("select[name='declareStatus'] option[value='"+(data.declareStatus ? data.declareStatus : "" )+"']").prop("selected",true)
				//发车时间 
				_tr.find("input[name='startTime']").val(data.startTime)
				_tr.find("input[name='takeDate']").val(data.takeDate)
				_tr.find("input[name='arriveDate']").val(data.arriveDate)
				_tr.find("input[name='billCode']").val(data.billCode)
			}
		});
	}
