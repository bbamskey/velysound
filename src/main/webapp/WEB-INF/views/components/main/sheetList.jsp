<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${items}" var="s">
	<li class="swiper-slide li">
		<div class="img">
			<p class="img_a">
				<img src="${s.imagePath}"
				     alt="${s.sheetTitle}"/>
			</p>
			<div class="btn">
				<a href="#pop_quick" class="btn_pop">
					<span>Quick</span>
				</a>
				<a href="#">Cart</a>
			</div>
		</div>
		<ul class="txt">
			<li class="s1"> ${s.sheetTitle} </li>
			<li class="s2">
				₩<fmt:formatNumber type="number" value="${s.sheetPrice}" pattern="#,###"/>
			</li>
		</ul>
		<!-- badge_tog -->
		<div class="badge_tog">
			<button onClick="btn_badge_tog(this);" class="btn_badge_tog">
				<span class="badge">${s.sheetOriginalKey}</span>
			</button>
			<div class="badge_line">
				<c:forEach items="${fn:split(s.sheetKeys, ';')}" var="k">
					<span class="badge ${s.sheetOriginalKey eq k ? "on" : ""}">${k}</span><!-- 활성화 on -->
				</c:forEach>
			</div>
		</div>
		<!-- //badge_tog -->
	</li>
</c:forEach>