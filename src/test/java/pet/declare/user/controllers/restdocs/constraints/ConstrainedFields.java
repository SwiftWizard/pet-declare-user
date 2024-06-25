package pet.declare.user.controllers.restdocs.constraints;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.SubsectionDescriptor;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

/*
    Wrapper class for constraints documentation in RESTDocs
 */

public class ConstrainedFields {
    private final ConstraintDescriptions constraintDescriptions;
    public ConstrainedFields(Class<?> input) {
        this.constraintDescriptions = new ConstraintDescriptions(input);
    }

    public FieldDescriptor withPath(String path) {
        var description = this.constraintDescriptions.descriptionsForProperty(path);

        if(description.isEmpty()){
            description.add("No constraint");
        }

        return PayloadDocumentation.fieldWithPath(path).attributes(key("constraints").value(StringUtils
                .collectionToDelimitedString(description, ". ")));
    }

    public SubsectionDescriptor subsectionWithPath(String path) {
        var subsection = PayloadDocumentation.subsectionWithPath(path);
        var description = this.constraintDescriptions.descriptionsForProperty(path);

        return (SubsectionDescriptor) subsection.attributes(key("constraints").value(StringUtils.collectionToDelimitedString(description, ". ")));

    }
}
