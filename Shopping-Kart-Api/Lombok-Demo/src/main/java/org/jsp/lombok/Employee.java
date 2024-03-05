package org.jsp.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

	private int id;
	private String name;
	private String desg;
	private int phone;
	private String email;
	private String password;
}
