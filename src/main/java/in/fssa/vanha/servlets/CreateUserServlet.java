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
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/home/user/create")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		StringBuilder requestBody = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			requestBody.append(line);
		}

		Gson gson = new Gson();
		User userRequest = gson.fromJson(requestBody.toString(), User.class);
		String name = userRequest.getName();
		String email = userRequest.getEmail();
		long number = userRequest.getNumber();
		String password = userRequest.getPassword();
		String location = userRequest.getLocation();
		
		try {
			UserService us = new UserService();
			User u = new User();
			u.setEmail(email);
			u.setPassword(password);
			u.setNumber(number);
			u.setName(name);
			u.setLocation(location);

			us.create(u);

			PrintWriter out = response.getWriter();
			
			// Prepare and send a JSON response
			ResponseEntity res = new ResponseEntity();
			res.setStatusCode(200);
			res.setData(u);
			res.setMessage("Register successful");

			String responseJson = gson.toJson(res);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(responseJson);

			out.flush();

		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
