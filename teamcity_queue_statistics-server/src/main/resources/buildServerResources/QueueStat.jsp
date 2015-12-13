<%@ include file="/include.jsp" %>
<bs:page>
  <jsp:attribute name="body_include">
    <h2>Hello <c:out value="${userName}"/>!</h2>

    <p>For the statistics collecting purpose we can observe the events that are happening with build queue</p>

    Found log messages:
    <c:forEach items="${messages}" var="message">
      <br><span class="mono mono-12px"><c:out value="${message}"/></span>
    </c:forEach>
  </jsp:attribute>
</bs:page>