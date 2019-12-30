$(function(){
	$(".form-horizontal.search .search-btn").click(function(){
		if($(this).closest(".sea").hasClass("retract")){
			$(this).closest(".sea").removeClass("retract");
			$(this).closest(".search").find(".hd").hide();
			$(this).find("f").text("展 开");
		}else{
			$(this).closest(".sea").addClass("retract");
			$(this).closest(".sea").siblings().show();
			$(this).find("f").text("收 起");
		}
	})

	if($(".card-box-top-nav").length>0){
		for(var i=0;i<$(".card-box-top-nav").length; i++){
			var _topNav=$(".card-box-top-nav").eq(i);
			_topNav.append('<div class="nav-tabs-bar"></div>');
			navBar(_topNav.find('.active'));
		}
		$('.card-box-top-nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		  navBar($(e.target).parent());
		})
	}
	console.info(2)
	//菜单
	try{
		$("#side-menu a[data-menu='"+data_menu+"']").parent().addClass("selected").parent().addClass("in").parent().addClass("active");
	}catch(e){
		
	}
})
function navBar($nav){
	bWidth=$nav.width();
	bLeft=$nav.find("a").offset().left-$nav.closest(".card-box-top-nav").offset().left;
	$nav.closest(".card-box-top-nav").find(".nav-tabs-bar").animate({left:bLeft+'px',width:bWidth+'px'});
}