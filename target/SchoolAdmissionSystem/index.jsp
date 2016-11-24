<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/login.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/importTool.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/global/header.css">
		<link href='http://fonts.googleapis.com/css?family=Lato:900,400' rel='stylesheet' type='text/css'>
		<title>Sign in - Montreal College</title>
	</head>
	<body>
		<div id="loginBody">
			<div id="login-bg"></div>
			<div id="login-div">
				
				<!-- Register -->
				<form id="login-form" action="login" method="post">
					<table id="login-table" class="middle-table">
						<thead>
							<tr>
								<td>
									<span class="table-header">LOGIN PORTAL</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<span id="login-user-error" class="error-text">Please input correct information.</span>
									<input id="login-user" type="text" placeholder="Username" name="user">
								</td>
							</tr>
							<tr>
								<td>
									<span id="login-password-error" class="error-text">Please input correct information.</span>
									<input id="login-password" type="password" placeholder="Password" name="password">
								</td>
							</tr>
							<tr>
								<td>
									<input id="login-submit" type="submit" value="Sign in" onclick="signin(event)">
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<span>
										Do not own an account?
										<span id="register-button" onclick="switchToRegister()">Register here</span>
									</span>
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
					
				<!-- Register -->
				<form id="register-form" action="register" method="post">
					<table id="register-table" class="middle-table">
						<thead>
							<tr>
								<td>
									<span class="table-header">REGISTRATION</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<span id="register-user-error" class="error-text">Please input correct information.</span>
									<input id="register-user" type="text" placeholder="Username" name="user">
								</td>
							</tr>
							<tr>
								<td>
									<span id="register-first-name-error" class="error-text">Please input correct information.</span>
									<input id="register-first-name" type="text" placeholder="First name" name="firstName">
								</td>
							</tr>
							<tr>
								<td>
									<span id="register-last-name-error" class="error-text">Please input correct information.</span>
									<input id="register-last-name" type="text" placeholder="Last name" name="lastName">
								</td>
							</tr>
							<tr>
								<td>
									<span id="register-email-error" class="error-text">Please input correct information.</span>
									<input id="register-email" type="email" placeholder="E-mail address" name="email">
								</td>
							</tr>
							<tr>
								<td>
									<span id="register-password-error" class="error-text">Please input correct information.</span>
									<input id="register-password" type="password" placeholder="Password" name="password">
								</td>
							</tr>
							<tr>
								<td>
									<span id="register-confirm-password-error" class="error-text">Please put matching password.</span>
									<input id="register-confirm-password" type="password" placeholder="Confirm password" name="confirm-password">
								</td>
							</tr>
							<tr>
								<td>
									<span style="font-size: 80%">By clicking register, you agree to the <a href="#">privacy policy</a> of the institution.</span>
								</td>
							</tr>
							<tr>
								<td>
									<input id="register-submit" type="submit" value="Register" onclick="register(event)">
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<span>
										Already own an account?
										<span id="register-button" onclick="switchToLogin()">Login here</span>
									</span>
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
			</div>
		</div>
		
		<!-- Loading screen -->
		<div id="black-div">
			<div class="loading-wrapper">
				<svg class="hourglass" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 120 206" preserveAspectRatio="none">
					<path class="middle" d="M120 0H0v206h120V0zM77.1 133.2C87.5 140.9 92 145 92 152.6V178H28v-25.4c0-7.6 4.5-11.7 14.9-19.4 6-4.5 13-9.6 17.1-17 4.1 7.4 11.1 12.6 17.1 17zM60 89.7c-4.1-7.3-11.1-12.5-17.1-17C32.5 65.1 28 61 28 53.4V28h64v25.4c0 7.6-4.5 11.7-14.9 19.4-6 4.4-13 9.6-17.1 16.9z"/>
					<path class="outer" d="M93.7 95.3c10.5-7.7 26.3-19.4 26.3-41.9V0H0v53.4c0 22.5 15.8 34.2 26.3 41.9 3 2.2 7.9 5.8 9 7.7-1.1 1.9-6 5.5-9 7.7C15.8 118.4 0 130.1 0 152.6V206h120v-53.4c0-22.5-15.8-34.2-26.3-41.9-3-2.2-7.9-5.8-9-7.7 1.1-2 6-5.5 9-7.7zM70.6 103c0 18 35.4 21.8 35.4 49.6V192H14v-39.4c0-27.9 35.4-31.6 35.4-49.6S14 81.2 14 53.4V14h92v39.4C106 81.2 70.6 85 70.6 103z"/>
				</svg>
				<div class="loading-text">
					...THINKING...
				</div>
			</div>
			<div class="confirmation-wrapper" onclick="closeConfirmation(); switchToLogin();">
				<div class="confirmation-text">
					ACCOUNT CREATED
				</div>
				<div class="confirmation-subtitle-text">
					<span>You will receive confirmation e-mail shortly</span>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<footer id="loginFoot">
			<button id="contactBtn" class="nav-button">Contact</button>
			<button id="aboutBtn" class="nav-button">About</button>
		</footer>
		
		<script type="text/javascript" src="scripts/jquery-3.1.0.min.js"></script>
		<script type="text/javascript" src="scripts/main.js"></script>
		<script type="text/javascript" src="scripts/login-validation.js"></script>
	</body>
</html>