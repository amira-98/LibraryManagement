import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.annotations.Parameter;

import metier.entities.Adherent;
import metier.entities.Compte;
import metier.entities.Emprunt;
import metier.entities.Livre;
import metier.entities.Oeuvre;
import metier.sessions.service.BibliothequeLocale;



@Stateless
@Path("/bibliotheque")
public class serviceRest {
	@EJB
	private BibliothequeLocale metier;

	@GET
	@Path("/listecategories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Categorie> consulterCategorie_()
	{
		return metier.consulterCategories();
	}
	@GET
	@Path("/oeuvres")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<metier.entities.Oeuvre> consulterOeuvres()
	{return metier.consulterOeuvres();
	}
	@GET
	@Path("/auteurs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Auteur> consulterAuteur()
	{return metier.consulterAuteurs();
	}

	@GET
	@Path("/categories/{idCat}/oeuvres")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParCat(@PathParam(value="idCat")int idCat)
	{return metier.consulterOeuvreParCategorie(idCat);
	}  
	@GET
	@Path("/auteurs/oeuvres/{idAut}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParAut(@PathParam(value="idAut")int idAut)
	{return metier.consulterOeuvreParAuteur(idAut);
	} 
	@GET
	@Path("/oeuvres/all/{idAut}/{idCat}/{mc}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParAutCATMC(@PathParam(value="idAut")int idAut,@PathParam(value="idCat")int idCat,@PathParam(value="mc") String mc)
	{
		
		return metier.consulterOeuvreParAutCatmc(idAut, idCat, mc);
	} 
	@GET
	@Path("/oeuvres/{idAut}/{mc}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParAutMC(@PathParam(value="idAut")int idAut,@PathParam(value="mc") String mc)
	{
		
		return metier.consulterOeuvreParAutmc(idAut, mc);
	} 
	@GET
	@Path("/oeuvres/{idCat}/{mc}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParCATMC(@PathParam(value="idCat")int idCat,@PathParam(value="mc") String mc)
	{
		
		return metier.consulterOeuvreParCatmc( idCat, mc);
	} 
	@GET
	@Path("/oeuvres/{idAut}/{idCat}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParAutCAT(@PathParam(value="idAut")int idAut,@PathParam(value="idCat")int idCat)
	{
		
		return metier.consulterOeuvreParAutCat(idAut, idCat);
	} 
	@GET
	@Path("/comptes/{login}/{motdepasse}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> consulterCompte(@PathParam(value="login")String login,@PathParam(value="motdepasse")String motdepasse)
	{return metier.consulterCompte(login,motdepasse);
	} 
	@GET
	@Path("/oeuvres/{mc}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<metier.entities.Oeuvre> consulterOeuvreParMC(@PathParam(value="mc")String mc)
	{return metier.consulterOeuvresParMotC(mc);
	}
	@GET
	@Path("/oeuvre/{idOeuv}")
	@Produces(MediaType.APPLICATION_JSON)
	public Oeuvre consulterOeuvreParMC(@PathParam(value="idOeuv")int idOeuv)
	{return metier.consulterOeuvre(idOeuv);
	}
	@GET
	@Path("/livre/{idOeuv}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> consulterLivreparOeuvre(@PathParam(value="idOeuv")int idOeuv)
	{return metier.consulterLivresParOeuvre(idOeuv);
	}
	@GET
	@Path("/emprunts/{idadh}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emprunt> consulterEmpruntsparadherent(@PathParam(value="idadh")int idAdh)
	{return metier.consulterEmpruntparadherent(idAdh);
	}
	@GET
	@Path("/adherent/{idadh}")
	@Produces(MediaType.APPLICATION_JSON)
	public Adherent consulterAdherent(@PathParam(value="idadh")int idAdh)
	{return metier.consulterAdherent(idAdh);
	}
	@GET
	@Path("/emprunt/{idadh}/{idlivre}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public void addemprunt(@PathParam(value="idadh")int idAdh,@PathParam(value="idlivre")int idLivre,@PathParam(value="date") String date)
	{
		try {
			metier.AjouterEmprunt(idLivre, idAdh, new SimpleDateFormat("dd-MM-yyyy").parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
