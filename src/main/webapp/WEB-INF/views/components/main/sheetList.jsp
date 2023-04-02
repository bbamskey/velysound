<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${items}" var="s">
	<li class="swiper-slide li">
		<div class="img">
			<p class="img_a">
				<img src=""
				     alt=""/>
			</p>
			<div class="btn">
				<a href="#pop_quick" class="btn_pop">
					<span>Quick</span>
				</a>
				<a href="#">Cart</a>
			</div>
		</div>
		<ul class="txt">
			<li class="s1">
				나연 – POP! 나연 – POP! 나연 –
				POP! 나연 – POP! 나연 – POP!
				나연 – POP!
			</li>
			<li class="s2">AUD$2.50</li>
		</ul>
		<!-- badge_tog -->
		<div class="badge_tog">
			<button onClick="btn_badge_tog(this);" class="btn_badge_tog">
				<span class="badge">B</span>
			</button>
			<div class="badge_line">
				<span class="badge on">All</span>
				<span class="badge">C</span>
				<span class="badge">C#</span>
				<span class="badge">D</span>
				<span class="badge">D#</span>
				<span class="badge">E</span>
				<span class="badge">F</span>
				<span class="badge">F#</span>
				<span class="badge">G</span>
				<span class="badge">G#</span>
			</div>
		</div>
		<!-- //badge_tog -->
	</li>
</c:forEach>