package co.com.botech.dto.email.templateStructure;

import co.com.botech.dto.email.EmailVariables;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SendParentEmailRequest implements EmailVariables {
	private String familyCode;
	private List<String> studentNames;
	private List<String> emails;
	private String schoolName;
}
