import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("bands", Band.all());
        model.put("template", "templates/bands.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("/bands/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/band-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/:id", (request, reponse) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      model.put("band", band);
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id", (request, response) -> {
      String name = request.queryParams("name");
      Band newBand = new Band(name);
      newBand.save();
      response.redirect("/bands/" + newBand.getId());
      return null;
    });

    post("/bands/:id/update", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      String newName = request.queryParams("update");
      band.update(newName);
      response.redirect("/bands/" + band.getId());
      return null;
    });





  }
}
