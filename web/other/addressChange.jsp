<%@ page import="idu0200.kliendid.model.CustomerAddress" %>
<%@ page import="idu0200.kliendid.common.ValidationResult" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	CustomerAddress item = (CustomerAddress) request.getAttribute("item");
	ValidationResult<CustomerAddress> result = (ValidationResult<CustomerAddress>) request.getAttribute("result");
%>

<div class="container">
	<%=result.renderExceptionList()%>

	<form class="form-horizontal" method="post" action="r">
		<input type="hidden" name="c" value="other"/>
		<input type="hidden" name="a" value="submitAddressChange"/>
		<input type="hidden" name="id" value="<%=item.getId()%>"/>

		<div class="control-group">
			<label class="control-label" for="zip">Postiindeks</label>

			<div class="controls">
				<input required type="text" id="zip" name="zip" value="<%=item.getZip()%>">
				<%=result.renderInlineHelp("zip")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="house">Maja</label>

			<div class="controls">
				<input required type="text" id="house" name="house" value="<%=item.getHouse()%>">
				<%=result.renderInlineHelp("house")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">Aadress</label>

			<div class="controls">
				<input required type="text" id="address" name="address" value="<%=item.getAddress()%>">
				<%=result.renderInlineHelp("address")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="county">Maakond</label>

			<div class="controls">
				<input required type="text" id="county" name="county" value="<%=item.getCounty()%>">
				<%=result.renderInlineHelp("county")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="parish">Vald</label>

			<div class="controls">
				<input required type="text" id="parish" name="parish" value="<%=item.getParish()%>">
				<%=result.renderInlineHelp("parish")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="town_county">Linn</label>

			<div class="controls">
				<input required type="text" id="town_county" name="town_county" value="<%=item.getTownCounty()%>">
				<%=result.renderInlineHelp("town_county")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="phone">Telefon</label>

			<div class="controls">
				<input required type="text" id="phone" name="phone" value="<%=item.getPhone()%>">
				<%=result.renderInlineHelp("phone")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sms">SMS</label>

			<div class="controls">
				<input required type="text" id="sms" name="sms" value="<%=item.getSms()%>">
				<%=result.renderInlineHelp("sms")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="mobile">Mobiil</label>

			<div class="controls">
				<input required type="text" id="mobile" name="mobile" value="<%=item.getMobile()%>">
				<%=result.renderInlineHelp("mobile")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="email">Email</label>

			<div class="controls">
				<input required type="email" id="email" name="email" value="<%=item.getEmail()%>">
				<%=result.renderInlineHelp("email")%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="note">M&auml;rkus</label>

			<div class="controls">
				<input required type="text" id="note" name="note" value="<%=item.getNote()%>">
				<%=result.renderInlineHelp("note")%>
			</div>
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Salvesta</button>
			<a href="r?c=customer&a=view&id=<%=item.getCustomer().getId()%>" class="btn">Katkesta</a>
		</div>
	</form>
</div>

<script>
	$(document).ready(function(){
		$(".control-group").each(function(index, control){
			var isError = $(control).find(".help-inline").length > 0;

			if (isError) {
				$(control).addClass('error');
			}
		});

	});

</script>