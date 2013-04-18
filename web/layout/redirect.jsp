<script>
	var a = ['c=<%=request.getAttribute("controller")%>', 'a=<%=request.getAttribute("action")%>']
	var id = '<%=request.getAttribute("id")%>'
	if (id.length > 0) {
		a.push('id=' + id);
	}
	window.location.href = "r?" + a.join('&');
</script>