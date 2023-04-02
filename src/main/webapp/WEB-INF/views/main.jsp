<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script src="/assets/js/page/main.js"></script>
<script src="/assets/js/data/video.js"></script>
<script src="/assets/js/data/sheet.js"></script>

<!-- container -->
<section id="container">
	<!-- wel_hero_banner -->
	<div class="wel_hero_banner">
		<!-- swiper-container -->
		<div class="swiper-container">
			<!-- swiper-wrapper -->
			<div class="swiper-wrapper">
				<!-- swiper-slide 반복 -->
				<%--				<div class="swiper-slide">--%>
				<%--					<p class="mb_bg">--%>
				<%--						<video src="/assets/images/contents/mc_visual.mp4" loop muted autoplay></video>--%>
				<%--					</p>--%>
				<%--					<div class="txt">--%>
				<%--						<dl>--%>
				<%--							<dt>--%>
				<%--								<p class="tit">POLUNIN X BOLSHOI</p>--%>
				<%--								<p class="tit02">--%>
				<%--									POLUNIN X BOLSHOI--%>
				<%--								</p>--%>
				<%--							</dt>--%>
				<%--							<dd class="s1">--%>
				<%--								산촌미학은 우리나라 전통 정과 떡--%>
				<%--								과편 등을--%>
				<%--								<br />--%>
				<%--								연구하는 한식 디저트--%>
				<%--								브랜드입니다.--%>
				<%--							</dd>--%>
				<%--						</dl>--%>
				<%--					</div>--%>
				<%--				</div>--%>
				<!-- //swiper-slide 반복 -->

				<div class="swiper-slide">
					<p class="mb_bg">
						<img src="/assets/images/banner/forest.jpg" alt=""/>
					</p>
					<div class="txt">
						<dl>
							<dt>
								<p class="tit">VELY SOUND</p>
								<p class="tit02">VELY SOUND</p>
							</dt>
							<dd class="s1">
								블라사운드에 방문해 주셔서<br/>정말 감사합니다 :)
							</dd>
						</dl>
					</div>
				</div>
				<div class="swiper-slide">
					<p class="mb_bg">
						<img src="/assets/images/banner/guitar.jpg" alt=""/>
					</p>
					<div class="txt">
						<dl>
							<dt>
								<p class="tit">PLAY & FUN</p>
								<p class="tit02">PLAY & FUN</p>
							</dt>
							<dd class="s1">
								당신의 삶을<br/>최고로 멋지게 연주하고<br/>최선을 다해 즐기세요 :)
							</dd>
						</dl>
					</div>
				</div>
			</div>
			<!-- //swiper-wrapper -->

			<!-- swiper_control -->
			<div class="swiper_control">
				<div class="swiper-button-prev"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
			</div>
			<!-- //swiper_control -->
		</div>
		<!-- //swiper-container -->
	</div>
	<!-- //wel_hero_banner -->

	<!-- cont_area -->
	<div class="cont_area">
		<!-- Video -->
		<div class="cont">
			<h2 class="b_tit">Video</h2>

			<!-- video_list -->
			<ul class="video_list" id="mainVideoUl">
			</ul>
			<!-- //video_list -->

			<!-- btn_area -->
			<div class="btn_area">
				<button class="btn_more_view_box">
					<strong>Watch More
						<em></em>
					</strong>
				</button>
			</div>
			<!-- //btn_area -->
		</div>
		<!-- //Video -->
	</div>
	<!-- //cont_area -->

	<!-- cont_area -->
	<div class="cont_area bg">
		<!-- Products -->
		<div class="cont">
			<h1 class="b_tit">Products</h1>

			<!-- thumb_list_wrap -->
			<div class="thumb_list_wrap">
				<!-- thumb_list_cont -->
				<div class="thumb_list_cont thumb_list">
					<ul class="swiper-wrapper" id="mainSheet1Ul">
					</ul>
				</div>
				<!-- //thumb_list_cont -->

				<!-- swiper_control -->
				<div class="swiper_control_bk">
					<div class="swiper-button-prev"></div>
					<div class="swiper-button-next"></div>
				</div>
				<!-- //swiper_control -->
			</div>
			<!-- //thumb_list_wrap -->

			<!-- thumb_list_wrap -->
			<div class="thumb_list_wrap">
				<!-- thumb_list_cont -->
				<div class="thumb_list_cont thumb_list">
					<ul class="swiper-wrapper" id="mainSheet2Ul">
					</ul>
				</div>
				<!-- //thumb_list_cont -->

				<!-- swiper_control -->
				<div class="swiper_control_bk">
					<div class="swiper-button-prev"></div>
					<div class="swiper-button-next"></div>
				</div>
				<!-- //swiper_control -->
			</div>
			<!-- //thumb_list_wrap -->
		</div>
		<!-- //Products -->
	</div>
	<!-- //cont_area -->
</section>
<!-- //container -->
