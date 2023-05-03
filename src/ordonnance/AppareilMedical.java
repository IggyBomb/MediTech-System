package ordonnance;

public class AppareilMedical {
	private String idAppareil;
    private String name;
    private String description;
    private String manufacturer;
    private String model;
    private boolean instance;
    private boolean octroye;


	public AppareilMedical(String id, String name, String description, String manufacturer, String model) {
		this.idAppareil = id;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.model = model;
        this.octroye = false;
        this.instance = false;
    }

	
	public String getIdAppareil() {
		return idAppareil;
	}


	public void setIdAppareil(String idAppareil) {
		this.idAppareil = idAppareil;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AppareilMedical [name=" + name + ", description=" + description + ", manufacturer=" + manufacturer
				+ ", model=" + model + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}
	

	public void setModel(String model) {
		this.model = model;
	}
	
    public boolean isInstance() {
		return instance;
	}

	public void setInstance(boolean emprunte) {
		this.instance = emprunte;
	}
	
	public boolean isOctroye() {
		return octroye;
	}

	public void setOctroye(boolean octroye) {
		this.octroye = octroye;
	}


}
