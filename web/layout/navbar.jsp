<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="brand" href="#">Kliendid</a>

			<div class="nav-collapse collapse">
				<p class="navbar-text pull-right">
					Andrei Reinus <a href="r?c=auth&a=logout" class="btn">Logi v&auml;lja</a>
				</p>
				<ul class="nav">
					<li><a href="r?c=customer&a=index">Kliendid</a></li>
					<li><a href="r?c=groups&a=index">Grupid</a></li>
					<li><a href="#contact">Contact</a></li>
					<li>

					</li>
				</ul>
				<form class="navbar-search pull-left" method="post" action="r">
					<input type="hidden" name="c" value="customer" />
					<input type="hidden" name="a" value="search" />

					<div class="controls">
						<div class="input-append">

							<input type="search" class="span3" placeholder="Search" name="search" id="search"/>

						<span class="add-on">
                        			<i class="icon-search"></i>
                    			</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>