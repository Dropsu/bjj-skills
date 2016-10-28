package linkwrappers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.ds.controllers.AccountController;
import com.ds.controllers.TechniqueController;
import com.ds.domain.Account;
import com.ds.domain.Technique;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AccountLinkWrapper extends ResourceSupport {

	private final Account account;

	public AccountLinkWrapper(Account account) { 
		super();
		this.account = account;
		this.add(linkTo(methodOn(TechniqueController.class).getTechniquesList(account.getId())).withSelfRel());	
		this.add(linkTo(TechniqueController.class).slash("id").withRel("To technique with <id>"));	
	}

	public Account getAccount() {
		return account;
	}

	
	
	
	
}
