package artcreator.creator;

import artcreator.creator.impl.CreatorImpl;
import artcreator.creator.port.Creator;
import artcreator.domain.DomainFactory;
import artcreator.domain.Image;
import artcreator.domain.Profile;
import artcreator.domain.Template;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.State.S;
import artcreator.statemachine.port.StateMachine;

import java.io.File;

public class CreatorFacade implements CreatorFactory, Creator {

    private CreatorImpl creator;
    private StateMachine stateMachine;

    @Override
    public Creator creator() {
        if (this.creator == null) {
            this.stateMachine = StateMachineFactory.FACTORY.stateMachine();
            this.creator = new CreatorImpl(stateMachine, DomainFactory.FACTORY.domain());
        }
        return this;
    }

    @Override
    public void loadImage(File path) {
        if (this.stateMachine.getState().isSubStateOf(S.LOAD_IMAGE) ||
                this.stateMachine.getState().isSubStateOf(S.IMAGE_LOADED)) {
            this.creator.loadImage(path);
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public void loadProfile(int id) {
        if (this.stateMachine.getState().isSubStateOf(S.IMAGE_LOADED)) {
            this.creator.loadProfile(id);
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public void generateTemplate() {
        if (this.stateMachine.getState().isSubStateOf(S.PROFILE_LOADED)) {
            this.creator.generateTemplate();
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public void saveTemplate(String targetPath) {
        if (this.stateMachine.getState().isSubStateOf(S.TEMPLATE_GENERATED)) {
            this.creator.saveTemplate(targetPath);
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public synchronized void sysop(String str) {
        if (this.stateMachine.getState().isSubStateOf(S.TEMPLATE_GENERATED /* choose right state*/))
            this.creator.sysop(str);
    }

    @Override
    public Image getCurrentImage() {
        if (this.stateMachine.getState().isSubStateOf(S.IMAGE_LOADED)) {
            return this.creator.getCurrentImage();
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public Profile getCurrentProfile() {
        if (this.stateMachine.getState().isSubStateOf(S.PROFILE_LOADED)) {
            return this.creator.getCurrentProfile();
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public Template getCurrentTemplate() {
        if (this.stateMachine.getState().isSubStateOf(S.TEMPLATE_GENERATED)) {
            return this.creator.getCurrentTemplate();
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public boolean getTemplateSaved() {
        if (this.stateMachine.getState().isSubStateOf(S.TEMPLATE_SAVED)) {
            return this.creator.getTemplateSaved();
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }
}
