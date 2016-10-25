package linkwrappers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.ds.controllers.AccountController;
import com.ds.controllers.TechniqueController;
import com.ds.domain.Technique;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class TechniqueLinkWrapper extends ResourceSupport {

	private final Technique technique;

	public TechniqueLinkWrapper(Technique technique) {
		super();
		this.technique = technique;
		this.add(linkTo(methodOn(TechniqueController.class).get(technique.getId())).withSelfRel());
		this.add(linkTo(methodOn(AccountController.class).get(technique.getAccount().getId())).withRel("Account"));
		this.add(linkTo(TechniqueController.class).withRel("techniques"));
		
	}

	public Technique getTechnique() {
		return technique;
	}
	
	
	
}
