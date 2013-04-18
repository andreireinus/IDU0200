<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
	<form class="form-signin" method="POST" action="/r">
		<input type="hidden" name="c" value="auth" />
		<input type="hidden" name="a" value="login" />
		<h2 class="form-signin-heading">Please sign in</h2>
		<input type="text" class="input-block-level" placeholder="Email address" name="username" value="andrei" />
		<input type="password" class="input-block-level" placeholder="Password" name="password" value="suva" />
		<button class="btn btn-large btn-primary" type="submit">Sign in</button>
	</form>
</div>
<style>
	.form-signin {
		width: 200px;
	}
</style>