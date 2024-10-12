package tacos.hateos;

import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Taco;
import tacos.web.controller.DesignTacoController;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {
    public TacoModelAssembler() {
        super(DesignTacoController.class, TacoModel.class);
    }

    @NotNull
    @Override
    protected TacoModel instantiateModel(@NotNull Taco entity) {
        return new TacoModel(entity);
    }

    @NotNull
    @Override
    public TacoModel toModel(@NotNull Taco entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
