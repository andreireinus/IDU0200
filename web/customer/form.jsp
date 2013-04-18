<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
	<form class="form-horizontal" method="post" action="r">
		<input type="hidden" name="c" value="customer" />
		<input type="hidden" name="a" value="submitNew" />

		<div class="control-group">
			<label class="control-label" for="first_name">Eesnimi</label>
			<div class="controls">
				<input type="text" id="first_name" name="first_name">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="last_name">Perenimi</label>
			<div class="controls">
				<input type="text" id="last_name" name="last_name">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="identity_code">Isikukood</label>
			<div class="controls">
				<input type="text" id="identity_code" name="identity_code">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="note">M&auml;rkus</label>
			<div class="controls">
				<input type="text" id="note" name="note">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="birth_date">S&uuml;nniaeg</label>
			<div class="controls">
				<input type="text" id="birth_date" name="birth_date">
			</div>
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Lisa</button>
			<a href="r?c=customer&a=index" class="btn">Katkesta</a>
		</div>
	</form>
</div>