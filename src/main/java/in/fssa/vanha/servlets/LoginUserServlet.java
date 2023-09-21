package in.fssa.vanha.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.fssa.vanha.exception.ServiceException;
import in.fssa.vanha.exception.ValidationException;
import in.fssa.vanha.model.ResponseEntity;
import in.fssa.vanha.model.User;
import in.fssa.vanha.service.UserService;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/home/login")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			BufferedReader reader = request.getReader();
			StringBuilder requestBody = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				requestBody.append(line);
			}

			Gson gson = new Gson();
			User userRequest = gson.fromJson(requestBody.toString(), User.class);

			String email = userRequest.getEmail();
			String password = userRequest.getPassword();

			UserService us = new UserService();
			User u = new User();
			u.setEmail(email);
			u.setPassword(password);

			User user = us.loginUser(u);
			
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(user);
			res.setMessage("Login successful");

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(responseJson);

			
		} catch (ValidationException e) {
			e.printStackTrace();
			// Handle validation errors and send an appropriate JSON response
		} catch (ServiceException e) {
			e.printStackTrace();
			// Handle service errors and send an appropriate JSON response
		}
		
		out.flush();

	}

}
