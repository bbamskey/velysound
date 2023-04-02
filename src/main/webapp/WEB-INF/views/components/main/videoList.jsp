<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${items}" var="v">
	<li>
		<div class="video_item">
			<iframe class="evt_video" src="${v.videoLink}"
			        title="YouTube video player" frameborder="0"
			        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
			        allowfullscreen muted></iframe>
			<p class="video_img">
				<img src="${v.videoThumbPath}" alt=""/>
			</p>
			<a href="#" class="video_link"></a>
		</div>
		<!-- btn_sns -->
		<div class="btn_sns">
			<a href="#" class="btn_sns_share ht_kakao">
				<span class="hidden">카카오톡 공유하기</span>
			</a>
			<a href="#" class="btn_sns_share ht_instar">
				<span class="hidden">인스타그램 공유하기</span>
			</a>
			<a href="#" class="btn_sns_share ht_facebook">
				<span class="hidden">페이스북 공유하기</span>
			</a>
			<a href="#" class="btn_sns_share ht_twitter">
				<span class="hidden">트위트 공유하기</span>
			</a>
			<a href="#" class="btn_sns_share ht_pinterest">
				<span class="hidden">핀터레스트 공유하기</span>
			</a>
			<a href="#" class="btn_sns_share ht_line">
				<span class="hidden">라인 공유하기</span>
			</a>
			<a href="#" class="btn_sns_share ht_message">
				<span class="hidden">메세지 공유하기</span>
			</a>
		</div>
		<!-- //btn_sns -->
	</li>
</c:forEach>