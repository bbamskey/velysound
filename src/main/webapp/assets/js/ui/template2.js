/*** Layout **/
$(function () {

	$(window).resize(resizeBox).resize();

	function resizeBox() {
		var locW = $(window).width();
		var locWs = 1282;
		var locWs2 = 782;

		function scrollEvent() {
			var winW = $(window).width();
			var winH = $(window).height();
			var locS = $(window).scrollTop();
			if (locS > 78) {
				$('header, #container').addClass('top');
			}
			if (locS < 78) {
				$('header, #container').removeClass('top');
			}
			//$('.wel_hero_banner .swiper-slide').css('height', winH  - locS ) ;    키비주얼 높이

		}

		$(window).scroll(function () {
			scrollEvent();
		});
		$(window).resize(function () {
			scrollEvent();
		});
		if (locW > locWs) {
			$('.btn_m_gnb_op').addClass('on');
			$('.btn_m_gnb_cl').removeClass('on');
			$('#gnb').removeClass('off').removeClass('on');
			$('#gnb').find('.dep').removeClass('over');
			$('.bg_mask').fadeOut();
		} else {
		}
		$('header').removeClass('over');
		$('.dep_m').removeClass('over');
		$('#gnb .dep').removeClass('over');
		$('#gnb .dep2').removeClass('over');
	}


	var header = $('header');
	var wrap = $('#wrap');
	wrap.append('<a href="#gnb" class=\"btn_m_gnb_op on\"><em>Menu Open</em></a>');
	header.append('<a href="#gnb" class=\"btn_m_gnb_cl\"><em>Menu Close</em></a>');
	header.prepend('<span class=\"bg_mask\"></span>');
	wrap.prepend('<span class=\"bg_mask2\"></span>');

	var nav_m_op = $('.btn_m_gnb_op');
	var nav_m_cl = $('.btn_m_gnb_cl, .bg_mask');
	var bg_mask = $('.bg_mask');
	var bg_mask2 = $('.bg_mask2');

	var gnb = $('#gnb');
	var gnb_m = $('.dep_m');
	var gnb_dep = $('#gnb .dep');
	var gnb_dep2 = $('#gnb .dep2');

	/* Mobile */
	nav_m_op.click(function () {
		var locW = $(window).width();
		var locWs = 1282;
		if (locW > locWs) {
		} else {
			header.addClass('ms')
			//nav_m_op.removeClass('on');
			nav_m_cl.addClass('on');
			gnb.addClass('on');
			bg_mask.fadeIn();
		}
		return false;
	});

	nav_m_cl.click(function () {
		var locW = $(window).width();
		var locWs = 1282;
		if (locW > locWs) {
		} else {
			nav_m_op.addClass('on');
			nav_m_cl.removeClass('on');
			gnb.addClass('off');
			bg_mask.fadeOut();
			setTimeout(function () {
				gnb.find('.dep').removeClass('over');
				gnb.removeClass('off').removeClass('on');
			}, 600);
		}
		return false;
	});

	gnb_m.click(function () {
		var locW = $(window).width();
		var locWs = 1282;
		var tar = $(this).closest('.dep');
		if (locW > locWs) {
			//return false;
		} else {
			var foldingChk = (tar.hasClass('over'));
			tar.removeClass('over');
			gnb.find('.dep').each(function () {
				$(this).removeClass('over');
			});
			if (foldingChk) {
				tar.removeClass('over');
			} else {
				tar.addClass('over');
			}
			return false;
		}
	});


	/* PC */
	gnb_m.bind('mouseover focusin', function () {
		var locW = $(window).width();
		var locWs = 1282;
		if (locW > locWs) {
			header.addClass('over');
			gnb_dep.removeClass('over');
			$(this).closest('.dep').addClass('over');
			bg_mask2.stop().fadeIn();//22.09.13
		}
	});
	gnb_m.bind('mouseout', function () {
		var locW = $(window).width();
		var locWs = 1282;
		if (locW > locWs) {
			//gnb_close();
		}
	});

	gnb_dep2.bind('mouseover', function () {
		gnb_m.removeClass('over');
		$(this).closest('.dep').find('.dep_m').addClass('over');
	});
	gnb_dep2.bind('mouseout', function () {
		gnb_m.removeClass('over');
	});
	header.mouseleave(function () {
		var locW = $(window).width();
		var locWs = 1282;
		if (locW > locWs) {
			gnb_close();
		}
	});
	$('h1, .utill_nav a, .btn_gnb_reserve').bind('mouseover focusin', function () {
		var locW = $(window).width();
		var locWs = 1282;
		if (locW > locWs) {
			gnb_close();
		}
	});

	function gnb_close() {
		header.removeClass('over');
		gnb_m.removeClass('over');
		gnb_dep.removeClass('over');
		gnb_dep2.removeClass('over');
		bg_mask2.stop().fadeOut();//22.09.13
	}

	$('.visual_area .title em, .visual_area .title i').lettering('');


	/* Breadcrumb */
	$('.breadcrumb .li .dep ul').css('min-width', $('.breadcrumb .li.act .link').innerWidth());
	$('.breadcrumb .link').bind('click mouseover', function () {
		if ($(this).closest('.li').hasClass('act')) {
			$(this).closest('.li').removeClass('act');
		} else {
			$('.breadcrumb .li').removeClass('act');
			$(this).closest('.li').addClass('act');
			$(this).closest('.li').find('.dep ul').css('min-width', $(this).closest('.li').find('.link').innerWidth() + 2);
		}
		return false;
	});
	$('.breadcrumb .dep, .breadcrumb').bind('mouseleave', function () {
		$('.breadcrumb .li').removeClass('act');
	});
	$('.breadcrumb .link').bind('focusin', function () {
		$('.breadcrumb .li').removeClass('act');
	});

	$('.breadcrumb .dep li:last-child a').keydown(function (e) {
		if (event.keyCode == 9) {
			$(this).closest('.li').find('.dep li').eq(0).find('a').focus();
			return false;
		}
	});
	$('.btn_search_s_op').click(function () {
		$('.btn_sel_search').addClass('on')
	});
	$('.btn_close_s').click(function () {
		$('.btn_sel_search').removeClass('on')
	});
});


/* btn_top */
function mc_progress() {
	var mc_progress = $('.mc_progress')
	var mc_value = mc_progress.attr('data-value') / mc_progress.attr('data-end');

	mc_progress.circleProgress({
		value: mc_value,
		startAngle: 4.75,
		size: 34,
		thickness: 2,
		lineCap: 'round',
		animation: {duration: 7000, easing: 'circleProgressEasing'},
		emptyFill: 'rgba(255,255,255,0)',
		fill: '#fff'
	});
}

$(function () {
	$(window).scroll(function () {
		if ($(this).scrollTop() > 100) {
			$('.btn_top, .btn_pay').addClass('on');
		} else {
			$('.btn_top, .btn_pay').removeClass('on');
		}
	});
	$('.btn_top').click(function () {
		;
		$('html, body').stop().animate({scrollTop: $('#wrap').offset().top}, 800);
		return false;
	});
	$('.btn_pay').click(function () {
		;
		$('html, body').stop().animate({scrollTop: $('.pro_info').offset().top - $('.header_cont').innerHeight() + 30}, 800);
		return false;
	});


	$(window).scroll(function () {
		scrollEvent2();
	});

	$(window).resize(function () {

	});

	function isMobile() {
		return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}

	function scrollEvent2() {
		if (isMobile()) {
			var scrollEnd = ($(document).height() - window.visualViewport.height) - $('.footer_box').innerHeight() + 10;
		} else {
			var scrollEnd = ($(document).height() - $(window).height()) - $('.footer_box').innerHeight() + 10;
		}

		if ($(window).scrollTop() > scrollEnd) {
			$('.btn_top, .btn_pay, .btn_m_gnb_op').addClass("act");
		} else {
			$('.btn_top, .btn_pay, .btn_m_gnb_op').removeClass("act");
		}
	}


	function scroll_button() {
		if (!$('.btn_top').length) return;

		// circle progress scroll
		$.circleProgress.defaults.animation = false;
		$.circleProgress.defaults.value = 0;
		//$.circleProgress.defaults.size = 65;
		$.circleProgress.defaults.startAngle = -Math.PI / 4 * 2;
		$.circleProgress.defaults.thickness = '2';
		$.circleProgress.defaults.emptyFill = '#eee';
		$.circleProgress.defaults.fill = {color: '#6234b2'};

		$('.btn_progress').circleProgress();
	}

	function scroll_button_resize() {
		$('.btn_progress').circleProgress('redraw');
		//$('html, body').stop().animate({scrollTop:$(window).scrollTop() + 1}, 0);//22.09.18 수정
		scrollEvent2();
	}

	function init_resize() {
		scroll_button_resize();
	}

	$(function () {
		scroll_button();
		$(window).on('resize', init_resize);
	});

});


$(function () {
	/* poup */
	$.fn.popOpen = function () {
		$(this).bind('click', function (e) {
			var s = $(this).attr("href");
			$(s).popup('show');
		});
		return this;
	};
	$('.btn_pop').popOpen();
});

function pop_close() {
	$('.modal').popup('hide');
}


$(function () {
	/* accordian_ty */
	$.fn.accordian_ty = function () {
		var tar = $(this).closest('.list');
		var foldingChk = tar.hasClass('on');
		if (foldingChk) {
			tar.removeClass('on');
		} else {
			//tar.siblings('.list').removeClass('on');
			tar.addClass('on');
		}
	};
	$('.accordian_ty .list .btn').click(function () {
		$(this).accordian_ty();
		return false;
	});
});


function quickareaSwiper() {
	var $target = $('.quick_area .img');
	$target.each(function (index, element) {
		var $parent = $(this).parent('.quick_area');
		var slideOption = {
			spaceBetween: 0,
			observer: true,
			observeParents: true,
			slidesPerView: 1,
			loop: true,
			autoHeight: true,
			speed: 1000,
			pagination: {
				el: ('.quick_area .swiper-pagination'),
			}
		};

		if ($parent.find('.swiper-slide').length > 1) {
			quickareaSwiperCont = new Swiper(this, slideOption);
		}
	});
}

$(function () {
	if (!$('.quick_area').length) return;
	quickareaSwiper();
});


function popProdViewSwiper() {
	var $target = $('.pop_prod_view_area .img');
	$target.each(function (index, element) {
		var $parent = $(this).parent('.pop_prod_view_area');
		var slideOption = {
			spaceBetween: 0,
			observer: true,
			observeParents: true,
			slidesPerView: 1,
			loop: true,
			autoHeight: true,
			speed: 1000,
			pagination: {
				el: ('.pop_prod_view_area .swiper-pagination'),
			}
		};

		if ($parent.find('.swiper-slide').length > 1) {
			popProdViewSwiperCont = new Swiper(this, slideOption);
		}
	});
}

$(function () {
	if (!$('.pop_prod_view_area').length) return;
	popProdViewSwiper();
});


function thumbListSwiper() {
	var $target = $('.thumb_list_cont');
	$target.each(function (index, element) {
		var $parent = $(this).parent('.thumb_list_wrap');
		var slideOption = {
			spaceBetween: 36,
			observer: true,
			observeParents: true,
			loop: true,
			slidesPerView: 4,
			spaceBetween: 30,
			breakpoints: {
				800: {
					slidesPerView: 2,
					spaceBetween: 20,
				},
				1300: {
					slidesPerView: 3,
					spaceBetween: 25,
				},
			},
			autoHeight: false,
			speed: 1000,
			navigation: {
				nextEl: $($parent).find('.swiper-button-next'),
				prevEl: $($parent).find('.swiper-button-prev'),
			},
		};

		//if($parent.find('.swiper-slide').length > 4) {
		thumbListSwiperCont = new Swiper(this, slideOption);
		$parent.addClass('swiper-on');
		//}
	});
}

$(function () {
	if (!$('.thumb_list_cont').length) return;
	thumbListSwiper();
});


/* Hero Banner */
function htHeroBanner() {
	var $target = $('.wel_hero_banner .swiper-container');
	$target.each(function (index, element) {
		var $parent = $(this).parent('.wel_hero_banner');
		$parent.addClass('hero_idx_' + index);

		var slideOption = {
			slidesPerView: 'auto',
			speed: 700,
			spaceBetween: 0,
			centeredSlides: true,
			loop: true,
			loopsSlide: 1,
			effect: 'fade',
			fadeEffect: {
				crossFade: true
			},
			autoplay: {
				delay: 5000,
				disableOnInteraction: false,
				//pauseOnMouseEnter: true,
			},
			navigation: {
				nextEl: ('.hero_idx_' + index + ' .swiper-button-next'),
				prevEl: ('.hero_idx_' + index + ' .swiper-button-prev'),
			},
			pagination: {
				el: ('.hero_idx_' + index + ' .swiper-pagination'),

			}
		};

		if ($parent.find('.swiper-slide').length > 1) {
			htHeroBannerSwiper = new Swiper(this, slideOption);
			$parent.addClass('swiper-on');
			$parent.find('.option_box').css('display', 'flex');
		}
	});
}

$(function () {
	if (!$('.wel_hero_banner').length) return;
	htHeroBanner();
	$('.wel_hero_banner .tit, .wel_hero_banner .tit02').lettering('');
	$('.wel_hero_banner .swiper-slide').css('height', $(window).height());
});


function prodThumb() {
	$('.pro_thumb_area li').each(function (i) {
		$(this).addClass('itm' + i);
		$('.pro_thumb_area li.itm0').addClass('selected');

		$(this).click(function () {
			$('.pro_img_area').trigger('slideTo', [i, 0, true]);
			return false;
		});
	});
	$('.pro_img_area').carouFredSel({
		responsive: true,
		firstLoadChk: true,
		direction: 'left',
		circular: true,
		infinite: false,
		auto: false,
		prev: '.pro_img_prev',
		next: '.pro_img_next',
		pagination: false,
		swipe: {onMouse: true, onTouch: true},
		scroll: {
			fx: 'directscroll',
			onBefore: function () {
				var pos = $(this).triggerHandler('currentPosition');
				$('.pro_thumb_area li').removeClass('selected');
				$('.pro_thumb_area li.itm' + pos).addClass('selected');
				var page = Math.floor(pos / 6);
				$('.pro_thumb_area').trigger('slideToPage', page);
			}
		}
	});
	$('.pro_thumb_area').carouFredSel({
		firstLoadChk: true,
		direction: 'left',
		circular: true,
		infinite: false,
		items: {start: 0, visible: 6},
		align: false,
		auto: false,
		prev: false,
		next: false
	});
}

function init_resize_prod() {
	prodThumb();
}

$(function () {
	if (!$('.pro_thumb_area').length) return;
	prodThumb();
	$(window).on('resize', init_resize_prod);

});


function btn_modify_s(val) {
	$(val).next('.btn_confirm_box').addClass('on');
}

function btn_cancel_s(val) {
	$(val).closest('.btn_confirm_box').removeClass('on');
}


function btn_badge_tog(val) {
	if ($(val).closest('.badge_tog').hasClass('on')) {
		$(val).closest('.badge_tog').removeClass('on');
	} else {
		$('.badge_tog').removeClass('on');
		$(val).closest('.badge_tog').addClass('on');
	}
}


$(function () {
	$('body').click(function (e) {
		if (!$('.badge_tog').has(e.target).length) {
			$('.badge_tog').removeClass('on');
		}
	});
});


$(function () {
	function isMobile() {
		return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}

	if (!$('.video_item').length) return;
	if (isMobile()) {
		$('.video_item').addClass('chk');
	}

	$('.video_link').click(function (e) {
		e.preventDefault();
		$(this).closest('.video_item').addClass('chk');
		$(this).closest('.video_item').find('iframe')[0].src += '?autoplay=1';
	});
});


/*** Mobile Mode Check : 팝업 스크롤 여백 ***/
$(function () {
	function htMobile() {
		return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}

	if (htMobile()) {
		$('body').addClass('ht_mo');
	}
});






