<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${items}" var="v">
	<li>
		<div class="video_item">
			<iframe class="evt_video" src="${v.videoLink}"
			        title="YouTube video player" frameborder="0"
			        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
			        allowfullscreen muted></iframe>
			<c:if test="${v.videoThumbPath ne null}">
				<p class="video_img">
					<img src="${v.videoThumbPath}" alt=""/>
				</p>
				<a href="#" class="video_link"></a>
			</c:if>
		</div>
		<div class="btn_key">
			<div class="badge_tog_v btn_sheet" style="width: auto; overflow: visible;">
				<c:if test="${v.sheetId ne null}">
					<p className="bt">
						<a href="javascript: void(0);" class="btn_box cursorPointer">
							<span>악보보기</span>
						</a>
					</p>
				</c:if>
					<%--				<c:if test="${v.sheetKeys ne null}">--%>
					<%--					<c:forEach items="${fn:split(v.sheetKeys, ';')}" var="k">--%>
					<%--						<div className="badge_tog_v">--%>
					<%--							<button className="btn_badge_tog_v">--%>
					<%--								<span class="badge ${v.sheetOriginalKey eq k ? "on" : ""}">${k}</span><!-- 활성화 on -->--%>
					<%--							</button>--%>
					<%--						</div>--%>
					<%--					</c:forEach>--%>
					<%--				</c:if>--%>
			</div>
		</div>
	</li>
</c:forEach>