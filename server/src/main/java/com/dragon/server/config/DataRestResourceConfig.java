package com.dragon.server.config;

import com.dragon.server.controller.ChildToSessionController;
import com.dragon.server.controller.SessionToChildController;
import com.dragon.server.entity.Child;
import com.dragon.server.entity.Session;
import com.dragon.server.service.BasePathAwareLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Configuration
public class DataRestResourceConfig {

    private BasePathAwareLinkService service;

    @Autowired
    public DataRestResourceConfig(BasePathAwareLinkService service) {
        this.service = service;
    }

    @Bean
    public ResourceProcessor<Resource<Session>> sessionProcessor() {

        return new ResourceProcessor<Resource<Session>>() {

            @Override
            public Resource<Session> process(Resource<Session> resource) {
                Session session = resource.getContent();
                resource.add(
                        service.underBasePath(
                            linkTo(methodOn(SessionToChildController.class).getAllPerformancesForSession(session.getId()))
                        ).withRel("performances")
                );
                return resource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Child>> childProcessor() {

        return new ResourceProcessor<Resource<Child>>() {

            @Override
            public Resource<Child> process(Resource<Child> resource) {

                Child child = resource.getContent();
                resource.add(
                        service.underBasePath(
                            linkTo(methodOn(ChildToSessionController.class).getAllPerformancesForChild(child.getId()))
                        ).withRel("performances"));
                return resource;
            }
        };
    }

}
