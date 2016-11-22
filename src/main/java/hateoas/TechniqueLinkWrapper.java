package hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.ds.controller.AccountController;
import com.ds.controller.TechniqueController;
import com.ds.domain.Technique;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class TechniqueLinkWrapper extends ResourceSupport {

	private final Technique technique;

	public TechniqueLinkWrapper(Technique technique) {
		super();
		this.technique = technique;
		this.add(linkTo(methodOn(TechniqueController.class).get(technique.getAccount().getUsername(),technique.getId())).withSelfRel());
		this.add(linkTo(AccountController.class).slash(technique.getAccount().getUsername()).withRel("Account"));
	}

	public Technique getTechnique() {
		return technique;
	}
	
	
	
}
