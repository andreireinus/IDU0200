$(document).ready(function(){
	if (currentObjectId == 0) {
		return;
	}

	$.ajax({
			url: 'ajax',
			type: 'GET',
			dataType: 'json',
			data: {
				'c': 'customers',
				'f': 'group',
				'v': currentObjectId
			},
			success: function(data) {
				var table = $("#group_customers_list");
				table.hide();

				var template;
				if (data.length > 0) {
					template = $("#tmpl-group-customers-list");
					table.children("tbody").html(template.tmpl(data));
					table.show();
					console.log(data);
				}
			}
		}
	);
});
