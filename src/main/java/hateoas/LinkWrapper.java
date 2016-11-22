package hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.ds.controller.AccountController;
import com.ds.controller.TechniqueController;
import com.ds.domain.Technique;

import java.security.Principal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class LinkWrapper extends ResourceSupport {

	private final Technique technique;

	public LinkWrapper(Technique technique) throws Exception {
		super();
		Principal principal = null;
		this.technique = technique;
		this.add(linkTo(methodOn(TechniqueController.class).get(technique.getAccount().getUsername(),technique.getId(),principal)).withSelfRel());
		this.add(linkTo(AccountController.class).slash(technique.getAccount().getUsername()).withRel("Account"));
	}

	public Technique getTechnique() {
		return technique;
	}
	
	
	
}
