package artcreator.creator;

import artcreator.creator.impl.CreatorImpl;
import artcreator.creator.port.Creator;
import artcreator.domain.*;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.State.S;

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
    public Image loadImage(String path) {
        if (this.stateMachine.getState().isSubStateOf(S.LOAD_IMAGE) ||
                this.stateMachine.getState().isSubStateOf(S.IMAGE_LOADED)) {
            return this.creator.loadImage(path);
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public Profile loadProfile(int id) {
        if (this.stateMachine.getState().isSubStateOf(S.IMAGE_LOADED)) {
            return this.creator.loadProfile(id);
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public Template generateTemplate() {
        if (this.stateMachine.getState().isSubStateOf(S.PROFILE_LOADED)) {
            return this.creator.generateTemplate();
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
    public boolean confirmTemplateCreation() {
        if (this.stateMachine.getState().isSubStateOf(S.TEMPLATE_SAVED)) {
            return this.creator.confirmTemplateCreation();
        } else {
            throw new IllegalStateException("Invalid state for operation.");
        }
    }

    @Override
    public synchronized void sysop(String str) {
        if (this.stateMachine.getState().isSubStateOf(S.TEMPLATE_GENERATED /* choose right state*/))
            this.creator.sysop(str);
    }
}
