<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<div class="header_cont">
		<p class="logo">
			<a href="/" class="moveTo" data-type="main">
				VELY SOUND
			</a>
		</p>

		<ul class="utill_nav">
			<li>
				<a
						href="//my"
						class="moveTo"
						data-type="myOrderHistory"
				>
					My
				</a>
			</li>
			<li>
				<a
						href="javascript: return false;"
						class="login"
						id="btn_login"
						onClick={() => _modalVisible("login")}
				>
				Login
				</a>
			</li>
			<li>
				<a
						href="javascript: return false;"
						class="cart"
						id="btn_cart"
						onClick={() => _modalVisible("cart")}
				>
				Cart
				{cartCount !== 0 && <em>{cartCount}</em>}
				</a>
			</li>
		</ul>

		<nav id="gnb">
			<h2 class="blind">GNB</h2>
			<ul class="gnb_nav">
				<li class="dep">
					<a href="/" class="dep_m home">
						<span>HOME</span>
					</a>
				</li>
				<li class="dep">
					<a href="javascript: return false;" class="dep_m">
						<span>VIDEO</span>
					</a>
					<div class="dep2">
						<ul class="li_dep">
							{ex_category.map(({ id, name }) => (
							<li class="li" key={id}>
								<a
										href="javascript: return false;"
										onClick={() =>
								_moveToVideo(id)
								}
								class="dep_m2"
								>
								{name}
								</a>
							</li>
							))}
						</ul>
					</div>
				</li>
				<li class="dep">
					<a href="javascript: return false;" class="dep_m">
						<span>SHEET</span>
					</a>
					<div class="dep2">
						<ul class="li_dep">
							{ex_category.map(({ id, name }) => (
							<li class="li" key={id}>
								<a
										to={"/sheet/" + id}
										class="dep_m2"
								>
									{name}
								</a>
							</li>
							))}
						</ul>
					</div>
				</li>
				<li class="dep">
					<a href="javascript: return false;" class="dep_m">
						<span>CONTACT</span>
					</a>
					<div class="dep2">
						<ul class="li_dep">
							<li class="li">
								<a
										href="//faq"
										class="dep_m2"
								>
									FAQ
								</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="dep">
					<a href="javascript: return false;" class="dep_m close">
						<span>CLOSE</span>
						<em>MENU CLOSE</em>
					</a>
				</li>
			</ul>

			<div class="utill_sns">
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_kakao"
				>
								<span class="hidden">
									카카오톡 공유하기
								</span>
				</a>
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_instar"
				>
								<span class="hidden">
									인스타그램 공유하기
								</span>
				</a>
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_facebook"
				>
								<span class="hidden">
									페이스북 공유하기
								</span>
				</a>
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_twitter"
				>
					<span class="hidden">트위트 공유하기</span>
				</a>
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_pinterest"
				>
								<span class="hidden">
									핀터레스트 공유하기
								</span>
				</a>
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_line"
				>
					<span class="hidden">라인 공유하기</span>
				</a>
				<a
						href="javascript: return false;"
						class="btn_sns_share ht_message"
				>
					<span class="hidden">메세지 공유하기</span>
				</a>
			</div>
		</nav>
	</div>
</header>