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

	public AccountLinkWrapper(Account account) { //TODO: Add link to its techniques
		super();
		this.account = account;
		this.add(linkTo(methodOn(AccountController.class).get(account.getId())).withSelfRel());		
	}

	public Account getAccount() {
		return account;
	}

	
	
	
	
}
