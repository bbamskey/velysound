<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header -->
<header>
	<!-- header_cont -->
	<div class="header_cont">
		<p class="logo">
			<a href="/">VELY SOUNDr</a>
		</p>

		<!-- utill_nav -->
		<ul class="utill_nav">
			<li>
				<a href="/my">My</a>
			</li>
			<li>
				<a href="#pop_login" class="login btn_pop">Login</a>
			</li>
			<li>
				<a href="#pop_cart" class="cart btn_pop">Cart
					<em>22</em>
				</a>
			</li>
		</ul>
		<!-- //utill_nav -->

		<!-- gnb -->
		<nav id="gnb">
			<h2 class="blind">GNB</h2>
			<!-- gnb_nav -->
			<ul class="gnb_nav">
				<li class="dep">
					<a href="/" class="dep_m home">
						<span>HOME</span>
					</a>
				</li>
				<li class="dep">
					<a href="#" class="dep_m">
						<span>VIDEO</span>
					</a>
					<div class="dep2">
						<ul class="li_dep">
							<c:forEach items="${ex_category}" var="category">
								<li class="li">
									<a href="#" onclick="_moveToVideo(${category.id})"
									   class="dep_m2">${category.name}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				<li class="dep">
					<a href="#" class="dep_m">
						<span>SHEET</span>
					</a>
					<div class="dep2">
						<ul class="li_dep">
							<c:forEach items="${ex_category}" var="category">
								<li class="li">
									<c:url var="sheetUrl" value="/sheet/${category.id}"/>
									<a href="${sheetUrl}" class="dep_m2">${category.name}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				<li class="dep">
					<a href="#" class="dep_m">
						<span>CONTACT</span>
					</a>
					<div class="dep2">
						<ul class="li_dep">
							<li class="li">
								<a href="/faq" class="dep_m2">FAQ</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="dep">
					<a href="#" class="dep_m close">
						<span>CLOSE</span>
						<em>MENU CLOSE</em>
					</a>
				</li>
			</ul>
			<!-- //gnb_nav -->

			<!-- utill_sns -->
			<div class="utill_sns">
				<a href="javascript: void(0);" class="btn_sns_share ht_kakao">
					<span class="hidden">
						카카오톡 공유하기
					</span>
				</a>
				<a href="javascript: void(0);" class="btn_sns_share ht_instar">
					<span class="hidden">
						인스타그램 공유하기
					</span>
				</a>
				<a href="javascript: void(0);" class="btn_sns_share ht_facebook">
					<span class="hidden">
						페이스북 공유하기
					</span>
				</a>
				<a href="javascript: void(0);" class="btn_sns_share ht_twitter">
					<span class="hidden">트위트 공유하기</span>
				</a>
				<a href="javascript: void(0);" class="btn_sns_share ht_pinterest">
					<span class="hidden">
						핀터레스트 공유하기
					</span>
				</a>
				<a href="javascript: void(0);" class="btn_sns_share ht_line">
					<span class="hidden">라인 공유하기</span>
				</a>
				<a href="javascript: void(0);" class="btn_sns_share ht_message">
					<span class="hidden">메세지 공유하기</span>
				</a>
			</div>
			<!-- //utill_sns -->
		</nav>
		<!-- //gnb -->
	</div>
	<!-- //header_cont -->
</header>
<!-- //header -->
