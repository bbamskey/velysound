//===================================
//commonJs function
//===================================
const commonJs = {
    delay: 200, //메인 상단배너 오류로 인한 딜레이 시간
    locWs: 1282,
    pageInit: function () {
        //console.log("%c >> pageInit << ", "background: #11aa11; color: #fff");

        $dom.init();

        __eventListener.basic();
        __eventListener.scroll();
        //__eventListener.observer();

        __fn__page.moveTo();
    },
    etc: function () {
        //visual_area
        if ($(".visual_area .title em, .visual_area .title i").length)
            $(".visual_area .title em, .visual_area .title i").lettering("");

        /* Breadcrumb */
        $(".breadcrumb .li .dep ul").css(
            "min-width",
            $(".breadcrumb .li.act .link").innerWidth()
        );

        /* Mobile Mode Check : 팝업 스크롤 여백 */
        if (__fn__common.isMobile()) {
            $("body").addClass("ht_mo");
        }
    },
    resize: function () {
        __fn__resize.resizeInit();
    },
    swiper: function () {
        //__fn__swiper.quickareaSwiper
        if ($(".quick_area").length) {
            __fn__swiper.quickareaSwiper();
        }

        //__fn__swiper.thumbListSwiper
        if ($(".thumb_list_cont").length) {
            __fn__swiper.thumbListSwiper();
        }

        //__fn__swiper.htHeroBanner
        if ($(".wel_hero_banner").length) {
            __fn__swiper.heroBannerSwiper();
            $(".wel_hero_banner .tit, .wel_hero_banner .tit02").lettering("");
            $(".wel_hero_banner .swiper-slide").css(
                "height",
                $(window).height()
            );
        }
    },
    carousel: function () {
        if (__fn__page.changeOption.loadCarouselThumb === undefined) return;

        if ($dom.pro_thumb_area.length) {
            __fn__thumb.prodThumb();
            $(window).on("resize", __fn__thumb.prodThumb);
        }

        __fn__page.changeOption.loadCarouselThumb = true;
    },
};

//===================================
//common function
//===================================
const __fn__common = {
    isMobile: function () {
        return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(
            navigator.userAgent
        );
    },
    gnbOverClose: function () {
        $dom.header.removeClass("over");
        $dom.dep_m.removeClass("over");
        $dom.gnb_dep.removeClass("over");
        $dom.gnb_dep2.removeClass("over");
        $dom.bg_mask2.fadeOut();
    },
    gnbClose: function () {
        $dom.btn_m_gnb_op.addClass("on");
        $(".btn_m_gnb_cl, .bg_mask").removeClass("on");
        $dom.gnb.addClass("off");
        $dom.bg_mask.fadeOut();
        setTimeout(function () {
            $dom.gnb.find(".dep").removeClass("over");
            $dom.gnb.removeClass("off").removeClass("on");
        }, 600);
    },
};

//===================================
//page function
//===================================
const __fn__page = {
    loading: false,
    changeOption: {},
    changePageInterval: function () {
    },
    changePage: function (opt) {
        // console.log(
        // 	"%c >> changePage << ",
        // 	"background: #11aa11; color: #fff",
        // 	opt
        // );
        const me = this;
        $dom.init();

        if (opt.load !== undefined) me.getChangeOption(opt.load);

        me.changePageInterval = setInterval(function () {
            console.log(
                "%c changePageInterval ",
                "background: #9000ff8b; color: #fff",
                me.changeOption.loadSwiperQuick,
                me.changeOption.loadSwiperThumb,
                me.changeOption.loadSwiperHero,
                me.changeOption.loadCarouselThumb
            );

            if (
                (me.changeOption.loadSwiperQuick === undefined ||
                    me.changeOption.loadSwiperQuick === true) &&
                (me.changeOption.loadSwiperThumb === undefined ||
                    me.changeOption.loadSwiperThumb === true) &&
                (me.changeOption.loadSwiperHero === undefined ||
                    me.changeOption.loadSwiperHero === true) &&
                (me.changeOption.loadCarouselThumb === undefined ||
                    me.changeOption.loadCarouselThumb === true)
            ) {
                //console.log("clearInterval!!");
                clearInterval(me.changePageInterval);
                __fn__page.loading = false;
            }

            commonJs.resize();
            commonJs.swiper();
            commonJs.carousel();

            __fn__scroll.scrollButton(); //스크롤 이벤트를 위해 초기화

            $(window).on("resize", __fn__scroll.scrollButtonResize);

            if (!opt.stay) window.scrollTo(0, 0);
        }, commonJs.delay);
    },
    getChangeOption: function (load) {
        let me = this;

        if (load.includes("swiperQuick"))
            me.changeOption.loadSwiperQuick = false;
        else me.changeOption.loadSwiperQuick = undefined;

        if (load.includes("swiperThumb"))
            me.changeOption.loadSwiperThumb = false;
        else me.changeOption.loadSwiperThumb = undefined;

        if (load.includes("swiperHero")) me.changeOption.loadSwiperHero = false;
        else me.changeOption.loadSwiperHero = undefined;

        if (load.includes("carouselThumb"))
            me.changeOption.loadCarouselThumb = false;
        else me.changeOption.loadCarouselThumb = undefined;
    },
    moveTo: function (option) {
        const me = this;
        if (me.loading === true) return;

        const url = window.location.pathname;

        me.changeOption = {};

        console.log(
            "%c >> moveTo << ",
            "background: #11aa11; color: #fff",
            "option.page",
            option && option.page,
            "option.stay",
            option && option.stay,
            "url",
            url
        );

        let opt = {};
        if (url === "/" || (option && option.page === "main")) {
            //swiperHero, swiperThumb
            opt = {load: ["swiperHero", "swiperThumb"]};
        } else if (
            url === "/maintainance" ||
            (option && option.page === "maintainance")
        ) {
            //swiperHero, swiperThumb
            opt = {load: ["swiperHero"]};
        } else if (url === "/sheet" || (option && option.page === "sheet")) {
        } else if (
            url.includes("/video") ||
            (option && option.page === "video")
        ) {
        } else if (
            url.includes("/view") ||
            (option && option.page === "view")
        ) {
            opt = {load: ["swiperThumb", "carouselThumb"]};
        } else if (url === "/pay" || (option && option.page === "pay")) {
        } else if (url === "/faq" || (option && option.page === "faq")) {
        } else if (
            url === "/myOrderHistory" ||
            (option && option.page === "myOrderHistory")
        ) {
        } else if (
            url === "/myChangePassword" ||
            (option && option.page === "myChangePassword")
        ) {
        }

        if (option && option.stay !== undefined)
            opt = {...opt, stay: option.stay};

        me.changePage(opt);
    },
    openModal: function (page) {
        const me = this;

        me.changeOption = {};

        console.log(
            "%c >> openTo << ",
            "background: #11aa11; color: #fff",
            "page",
            page
        );

        let opt = {};
        if (page === "quick") {
            //swiperQuick
            opt = {load: ["swiperQuick"]};
        }

        opt = {...opt, stay: true};

        me.changePage(opt);
    },
};

//===================================
//scroll function
//===================================
const __fn__resize = {
    resizeInit: function () {
        $dom.wrap.append(
            '<a href="#gnb" class="btn_m_gnb_op on"><em>Menu Open</em></a>'
        );

        if (!$dom.header.find(".bg_mask").length)
            $dom.header.prepend('<span class="bg_mask"></span>');

        if (!$dom.wrap.find(".bg_mask2").length)
            $dom.wrap.prepend('<span class="bg_mask2"></span>');

        $dom.btn_m_gnb_op = $(".btn_m_gnb_op");
        $dom.bg_mask = $(".bg_mask");
        $dom.bg_mask2 = $(".bg_mask2");
    },
    resizeBox: function () {
        let locW = $(window).width();
        let locWs = commonJs.locWs;

        $(window).scroll(function () {
            __fn__scroll.scrollEvent();
            __fn__scroll.scrollEvent2();
        });

        $(window).resize(function () {
            __fn__scroll.scrollEvent();
            //__fn__scroll.scrollEvent2();
        });

        if (locW > locWs) {
            $dom.btn_m_gnb_op.addClass("on");
            $dom.btn_m_gnb_cl.removeClass("on");
            $dom.gnb.removeClass("off").removeClass("on");
            $dom.gnb.find(".dep").removeClass("over");
            $dom.bg_mask.fadeOut();
        }

        $dom.header.removeClass("over");
        $dom.dep_m.removeClass("over");
        $dom.gnb_dep.removeClass("over");
        $dom.gnb_dep2.removeClass("over");
    },
};

//===================================
//scroll function
//===================================
const __fn__scroll = {
    scrollButton: function () {
        if (!$dom.btn_top.length) return;

        // circle progress scroll
        $.circleProgress.defaults.animation = false;
        $.circleProgress.defaults.value = 0;
        //$.circleProgress.defaults.size = 65;
        $.circleProgress.defaults.startAngle = (-Math.PI / 4) * 2;
        $.circleProgress.defaults.thickness = "2";
        $.circleProgress.defaults.emptyFill = "#eee";
        $.circleProgress.defaults.fill = {color: "#6234b2"};

        $dom.btn_progress.circleProgress();
    },
    scrollButtonResize: function () {
        $dom.btn_progress.circleProgress("redraw");
        // $("html, body")
        // 	.stop()
        // 	.animate({ scrollTop: $(window).scrollTop() + 1 }, 0);
    },
    scrollEvent: function () {
        let winW = $(window).width();
        let winH = $(window).height();
        let locS = $(window).scrollTop();
        if (locS > 78) {
            $("header, #container").addClass("top");
        }
        if (locS < 78) {
            $("header, #container").removeClass("top");
        }
    },
    scrollEvent2: function () {
        let scrollEnd;
        if (__fn__common.isMobile()) {
            scrollEnd =
                $(document).height() -
                window.visualViewport.height -
                $(".copyrigth").innerHeight() +
                10;
        } else {
            // footer와 상관없이
            scrollEnd = $(document).height() - $(window).height() + 10;
        }

        if ($(window).scrollTop() > scrollEnd) {
            $dom.btn_top.addClass("act");
            $dom.btn_tog_pay.addClass("act");
            $dom.btn_m_gnb_op.addClass("act");
        } else {
            $dom.btn_top.removeClass("act");
            $dom.btn_tog_pay.removeClass("act");
            $dom.btn_m_gnb_op.removeClass("act");
        }
    },
    mcProgress: function () {
        //현재 미사용 : 진행바 로직으로 추정
        let mc_progress = $(".mc_progress");
        let mc_value =
            mc_progress.attr("data-value") / mc_progress.attr("data-end");

        mc_progress.circleProgress({
            value: mc_value,
            startAngle: 4.75,
            size: 34,
            thickness: 2,
            lineCap: "round",
            animation: {
                duration: 7000,
                easing: "circleProgressEasing",
            },
            emptyFill: "rgba(255,255,255,0)",
            fill: "#fff",
        });
    },
};

//===================================
//swiper function
//===================================
const __fn__swiper = {
    quickareaSwiper: function () {
        //퀵화면 악보 썸네일 스와이퍼
        if (
            __fn__page.changeOption.loadSwiperQuick === undefined ||
            __fn__page.changeOption.loadSwiperQuick === true
        )
            return;

        let $target = $(".quick_area .img");
        $target.each(function (index, element) {
            let $parent = $(this).parent(".quick_area");
            let slideOption = {
                spaceBetween: 0,
                observer: true,
                observeParents: true,
                slidesPerView: 1,
                loop: false,
                autoHeight: true,
                speed: 1000,
                pagination: {
                    el: ".quick_area .swiper-pagination",
                },
            };

            if ($parent.find(".swiper-slide").length > 1) {
                quickareaSwiperCont = new Swiper(this, slideOption);
            }
        });

        __fn__page.changeOption.loadSwiperQuick = true;
    },
    thumbListSwiper: function () {
        //악보 스와이퍼
        if (
            __fn__page.changeOption.loadSwiperThumb === undefined ||
            __fn__page.changeOption.loadSwiperThumb === true
        )
            return;

        let $target = $(".thumb_list_cont");
        $target.each(function (index, element) {
            let $parent = $(this).parent(".thumb_list_wrap");
            let slideOption = {
                spaceBetween: 36,
                observer: true,
                observeParents: true,
                loop: false,
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
                    nextEl: $($parent).find(".swiper-button-next"),
                    prevEl: $($parent).find(".swiper-button-prev"),
                },
            };

            thumbListSwiperCont = new Swiper(this, slideOption);
            $parent.addClass("swiper-on");
        });

        __fn__page.changeOption.loadSwiperThumb = true;
    },
    heroBannerSwiper: function () {
        //메인 탑 히어로배너
        if (
            __fn__page.changeOption.loadSwiperHero === undefined ||
            __fn__page.changeOption.loadSwiperHero === true
        )
            return;

        let $target = $(".wel_hero_banner .swiper-container");
        $target.each(function (index, element) {
            let $parent = $(this).parent(".wel_hero_banner");
            $parent.addClass("hero_idx_" + index);

            let slideOption = {
                slidesPerView: "auto",
                speed: 700,
                spaceBetween: 0,
                centeredSlides: true,
                loop: true,
                loopsSlide: 1,
                effect: "fade",
                fadeEffect: {
                    crossFade: true,
                },
                autoplay: {
                    delay: 5000,
                    disableOnInteraction: false,
                    //pauseOnMouseEnter: true,
                },
                navigation: {
                    nextEl: ".hero_idx_" + index + " .swiper-button-next",
                    prevEl: ".hero_idx_" + index + " .swiper-button-prev",
                },
                pagination: {
                    el: ".hero_idx_" + index + " .swiper-pagination",
                },
            };

            if ($parent.find(".swiper-slide").length > 1) {
                htHeroBannerSwiper = new Swiper(this, slideOption);
                if (
                    $(".wel_hero_banner .tit, .wel_hero_banner .tit02").length
                ) {
                    $parent.addClass("swiper-on");
                    $parent.find(".option_box").css("display", "flex");
                }
            }
        });

        __fn__page.changeOption.loadSwiperHero = true;
    },
};

//===================================
//thumb function
//===================================
const __fn__thumb = {
    prodThumb: function () {
        //악보 상세 화면 제품 썸네일
        if (
            __fn__page.changeOption.loadCarouselThumb === undefined ||
            __fn__page.changeOption.loadCarouselThumb === true
        )
            return;

        $(".pro_thumb_area li").each(function (i) {
            $(this).addClass("itm" + i);
            $(".pro_thumb_area li.itm0").addClass("selected");

            $(this).click(function () {
                $dom.pro_img_area.trigger("slideTo", [i, 0, true]);
                return false;
            });
        });
        $dom.pro_img_area.carouFredSel({
            responsive: true,
            firstLoadChk: true,
            direction: "left",
            circular: true,
            infinite: false,
            auto: false,
            prev: ".pro_img_prev",
            next: ".pro_img_next",
            pagination: false,
            swipe: {onMouse: true, onTouch: true},
            scroll: {
                fx: "directscroll",
                onBefore: function () {
                    let pos = $(this).triggerHandler("currentPosition");
                    $(".pro_thumb_area li").removeClass("selected");
                    $(".pro_thumb_area li.itm" + pos).addClass("selected");
                    let page = Math.floor(pos / 6);
                    $dom.pro_thumb_area.trigger("slideToPage", page);
                },
            },
        });
        $dom.pro_thumb_area.carouFredSel({
            firstLoadChk: true,
            direction: "left",
            circular: true,
            infinite: false,
            items: {start: 0, visible: 6},
            align: false,
            auto: false,
            prev: false,
            next: false,
        });

        __fn__page.changeOption.loadCarouselThumb = true;
    },
};

//===================================
//event listener
//===================================
const __eventListener = {
    basic: function () {
        const me = this;

        $("body").click(function (e) {
            if (!$dom.badge_tog.has(e.target).length) {
                $dom.badge_tog.removeClass("on");
            }
        });

        $(document).on("click", "a", function (e) {
            e.preventDefault();
        });

        $(document).on("click", ".moveTo", function (e) {
            __fn__page.moveTo({page: [$(this).data("type")]});
            __fn__page.loading = true;
        });

        //top버튼 클릭시 이동
        $(document).on("click", ".btn_top", function () {
            $("html, body")
                .stop()
                .animate({scrollTop: $dom.wrap.offset().top}, 800);
            return false;
        });

        //결제하기버튼 클릭시 이동
        $(document).on("click", ".btn_pay", function () {
            $("html, body")
                .stop()
                .animate(
                    {
                        scrollTop:
                            $(".pro_info").offset().top -
                            $(".header_cont").innerHeight() +
                            30,
                    },
                    800
                );
            return false;
        });

        //장바구니 수정 버튼
        $(document).on("click", ".btn_modify_s", function () {
            let val = this;
            $(val).next(".btn_confirm_box").addClass("on");
        });

        //장바구니 취소 버튼
        $(document).on("click", ".btn_cancel_s", function (e) {
            e.stopImmediatePropagation();
            let val = this;
            $(val).closest(".btn_confirm_box").removeClass("on");
        });

        //악보 키 미니 팝업 열기
        $(document).on("click", ".btn_badge_tog", function (e) {
            e.stopImmediatePropagation();
            let val = this;
            if ($(val).closest(".badge_tog").hasClass("on")) {
                $(val).closest(".badge_tog").removeClass("on");
            } else {
                $dom.badge_tog.removeClass("on");
                $(val).closest(".badge_tog").addClass("on");
            }
        });

        //===================================
        //video S
        //===================================
        //정렬 버튼 : sheet 공통
        $(document).on("click", "input[name=sale_chk]", function (e) {
            e.stopImmediatePropagation();
            let val = this;
            let tag = ".btn_sel";
            let listVar = $(val).closest("li").find("label").text();
            $(val).closest(tag).find(".link").text(listVar);
            $(tag).removeClass("act");
        });
        //===================================
        //video E
        //===================================

        //===================================
        //sheet S
        //===================================
        //악보 보기 타입 버튼
        $(document).on("click", ".btn_chk button", function () {
            if ($(".btn_chk").hasClass("on")) {
                $(".btn_chk").removeClass("on");
                $(".thumb_list").addClass("ty02");
            } else {
                $(".btn_chk").addClass("on");
                $(".thumb_list").removeClass("ty02");
            }
            return false;
        });
        //===================================
        //sheet E
        //===================================

        //비디오 썸네일 모바일 체크
        if ($(".video_item").length) {
            if (__fn__common.isMobile()) {
                $(".video_item").addClass("chk");
            }
        }

        //비디오 썸네일 클릭시 영상 재생
        $(document).on("click", ".video_link", function (e) {
            e.preventDefault();
            $(this).closest(".video_item").addClass("chk");
            $(this).closest(".video_item").find("iframe")[0].src +=
                "?autoplay=1";
        });

        //퀵 팝업 오픈
        $(document).on("click", ".openModal", function (e) {
            __fn__page.openModal("quick");
        });

        /* faq : accordian_ty */
        $(document).on("click", ".accordian_ty .list .btn", function (e) {
            e.stopImmediatePropagation();
            let tar = $(this).closest(".list");
            if (tar.hasClass("on")) {
                tar.removeClass("on");
            } else {
                //tar.siblings('.list').removeClass('on');
                $(".accordian_ty").children(".list").removeClass("on");
                tar.addClass("on");
            }

            __fn__page.moveTo({stay: true});
            __fn__page.loading = true;
        });

        /////////////////
        /* Mobile */
        $(document).on("click", ".btn_m_gnb_op", function (e) {
            let locW = $(window).width();
            let locWs = commonJs.locWs;
            if (locW <= locWs) {
                $dom.header.addClass("ms");
                $(".btn_m_gnb_cl, .bg_mask").addClass("on");
                $dom.gnb.addClass("on");
                $dom.bg_mask.fadeIn();
            }
            return false;
        });
        $(document).on("click", ".btn_m_gnb_cl, .bg_mask", function (e) {
            let locW = $(window).width();
            let locWs = commonJs.locWs;
            if (locW <= locWs) {
                __fn__common.gnbClose();
            }
            return false;
        });
        $(document).on("click", ".dep_m", function (e) {
            if ($(this).siblings(".dep2").find(".li").length === 0) {
                __fn__common.gnbClose();

                if ($(this).hasClass("close")) return;

                __fn__page.moveTo({});
                __fn__page.loading = true;
                return;
            }

            let locW = $(window).width();
            let locWs = commonJs.locWs;
            let tar = $(this).closest(".dep");
            if (locW <= locWs) {
                let foldingChk = tar.hasClass("over");
                tar.removeClass("over");
                $dom.gnb.find(".dep").each(function () {
                    $(this).removeClass("over");
                });
                if (foldingChk) {
                    tar.removeClass("over");
                } else {
                    tar.addClass("over");
                }
                return false;
            }
        });
        $(document).on("click", "#gnb .dep2", function (e) {
            let locW = $(window).width();
            let locWs = commonJs.locWs;
            if (locW > locWs) {
            } else {
                $dom.btn_m_gnb_op.addClass("on");
                $(".btn_m_gnb_cl, .bg_mask").removeClass("on");
                $dom.gnb.addClass("off");
                $dom.bg_mask.fadeOut();
                setTimeout(function () {
                    $dom.gnb.find(".dep").removeClass("over");
                    $dom.gnb.removeClass("off").removeClass("on");

                    __fn__page.moveTo({});
                    __fn__page.loading = true;
                }, 200);
            }
        });
        /* PC */
        $(document).on("mouseover", ".dep_m", function (e) {
            e.stopImmediatePropagation();
            let locW = $(window).width();
            let locWs = commonJs.locWs;
            if (locW > locWs) {
                $dom.header.addClass("over");
                $dom.gnb_dep.removeClass("over");
                $(this).closest(".dep").addClass("over");
                $dom.bg_mask2.fadeIn();
            }
        });

        $(document).on("mouseover", "#gnb .dep2", function (e) {
            $dom.dep_m.removeClass("over");
            $(this).closest(".dep").find(".dep_m").addClass("over");
        });

        $(document).on("mouseleave", "header", function (e) {
            let locW = $(window).width();
            let locWs = commonJs.locWs;
            if (locW > locWs) {
                __fn__common.gnbOverClose();
            }
        });

        //PC 헤더 클릭시 메뉴 닫힘
        $(document).on("click", ".dep_m2", function (e) {
            __fn__common.gnbOverClose();
            __fn__page.moveTo();
            __fn__page.loading = true;
        });

        $(document).on("click mouseover", ".breadcrumb .link", function (e) {
            e.stopImmediatePropagation();
            let val = this;
            if ($(val).closest(".li").hasClass("act")) {
                $(val).closest(".li").removeClass("act");
            } else {
                $(".breadcrumb .li").removeClass("act");
                $(val).closest(".li").addClass("act");
                $(val)
                    .closest(".li")
                    .find(".dep ul")
                    .css(
                        "min-width",
                        $(val).closest(".li").find(".link").innerWidth() + 2
                    );
            }
            return false;
        });

        $(document).on(
            "mouseleave",
            ".breadcrumb .dep, .breadcrumb",
            function () {
                $(".breadcrumb .li").removeClass("act");
            }
        );

        $(document).on("focusin", ".breadcrumb .link", function () {
            $(".breadcrumb .li").removeClass("act");
        });

        $(document).on("click", ".breadcrumb .act .dep li", function () {
            $(".breadcrumb .li").removeClass("act");
        });

        $(document).on(
            "keydown",
            ".breadcrumb .dep li:last-child a",
            function (e) {
                if (e.keyCode == 9) {
                    $(this)
                        .closest(".li")
                        .find(".dep li")
                        .eq(0)
                        .find("a")
                        .focus();
                    return false;
                }
            }
        );
        $(document).on("click", ".btn_search_s_op", function () {
            $(".btn_sel_search").addClass("on");
            console.log($(".li .act"));
            $(".li.act").removeClass("act");
        });

        //검색창 버튼 닫기 : 검색창 닫기
        $(document).on("click", ".btn_close_s", function () {
            $(".btn_sel_search").removeClass("on");
            $(".li.act").removeClass("act");
        });

        //검색창 돋보기 버튼 : 검색창 닫기
        $(document).on("click", ".btn_search_s", function () {
            $(".btn_sel_search").removeClass("on");
            $(".li.act").removeClass("act");
        });

        $(".search_form input").on("keypress", function (e) {
            if (e.keyCode === 13) {
                $(".btn_sel_search").removeClass("on");
                $(".li.act").removeClass("act");
            }
        });

        //탭 클릭
        $(document).on("click", ".tab_ty", function () {
            __fn__page.moveTo({});
            __fn__page.loading = true;
        });

        $(".search_form").on("submit", function () {
            $(".btn_sel_search.on").removeClass("on");
        });
    },
    scroll: function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 100) {
                $dom.btn_tog_pay.addClass("on");
            } else {
                $dom.btn_tog_pay.removeClass("on");
            }
        });

        $(window).resize(__fn__resize.resizeBox).resize();
    },
    observer: function () {
        console.log("observer", $(".thumb_list_wrap").length);
        setTimeout(function () {
            $(".thumb_list_wrap").each(function (e, i) {
                const target = $(this)[0];

                const ob = new MutationObserver((o) => {
                    console.log("observer");
                    __fn__swiper.quickareaSwiper();
                });

                const config = {
                    attributes: true,
                    childList: true,
                    //characterData: true,
                };

                ob.observe(target, config);
            });
            //ob.disconnect();
        }, commonJs.delay);
    },
};

//===================================
//dom
//===================================
const $dom = {
    header: {},
    wrap: {},
    btn_top: {},
    btn_tog_pay: {},
    btn_progress: {},
    badge_tog: {},
    dep_m: {},
    dep_m2: {},
    btn_m_gnb_op: {},
    btn_m_gnb_cl: {},
    gnb: {},
    gnb_dep: {},
    gnb_dep2: {},
    bg_mask: {},
    bg_mask2: {},
    pro_thumb_area: {},
    pro_img_area: {},
    init: function () {
        let me = this;
        me.header = $("header");
        me.wrap = $("#wrap");
        me.btn_top = $(".btn_top");
        me.btn_tog_pay = $(".btn_top, .btn_pay");
        me.btn_progress = $(".btn_progress");
        me.badge_tog = $(".badge_tog");
        me.dep_m = $(".dep_m");
        me.dep_m2 = $(".dep_m2");
        me.btn_m_gnb_op = $(".btn_m_gnb_op"); //생성 후에 할당됨 resizeInit
        me.btn_m_gnb_cl = $(".btn_m_gnb_cl");
        me.gnb = $("#gnb");
        me.gnb_dep = $("#gnb .dep");
        me.gnb_dep2 = $("#gnb .dep2");
        me.bg_mask = $(".bg_mask"); //생성 후에 할당됨 resizeInit
        me.bg_mask2 = $(".bg_mask2"); //생성 후에 할당됨 resizeInit
        me.pro_thumb_area = $(".pro_thumb_area");
        me.pro_img_area = $(".pro_img_area");
    },
};

//===================================
//document.ready
//===================================
$(function () {
    /* 서버 연결 안될 경우 S */
    const url = window.location.pathname;
    if (url === "/maintainance") {
        commonJs.pageInit();
        return;
    }
    /* 서버 연결 안될 경우 E */

    /* 서버 연결 테스트 S */
    $.ajax({
        url: "http://125.142.104.46:3333/api/cm/test", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
        data: {}, // HTTP 요청과 함께 서버로 보낼 데이터
        method: "GET", // HTTP 요청 메소드(GET, POST 등)
        dataType: "json", // 서버에서 보내줄 데이터의 타입
        contentType: "application/json;charset=UTF-8",
    })
        .done(function () {
            console.log(
                "%c >> 서버 정상 동작중 << ",
                "background: #11aa11; color: #fff"
            );
            console.log(
                "%c >> commonJs ready << ",
                "background: #11aa11; color: #fff"
            );
            setTimeout(() => {
                // console.log(
                // 	"%c >> commonJs setTimeout << ",
                // 	"background: #11aa11; color: #fff"
                // );
                commonJs.pageInit();
            }, commonJs.delay);

            /* 뒤로가기 시 스크립트 재로드 */
            window.history.pushState(null, null, window.location.href);
            window.onpopstate = function (e) {
                //history.go(1);
                console.log("뒤로가기닷##");
                __fn__page.moveTo({});
                __fn__page.loading = true;
            };
        })
        .fail(function () {
            console.log(
                "%c >> 서버 점검 중... << ",
                "background: #aa1111; color: #fff"
            );
            window.location.href = "/maintainance";
        });
    /* 서버 연결 테스트 E */
});
