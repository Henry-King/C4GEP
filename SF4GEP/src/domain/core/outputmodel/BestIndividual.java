package domain.core.outputmodel;



/**
 * BestIndividual entity. @author MyEclipse Persistence Tools
 */

public class BestIndividual  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -3893835633459421563L;
	/**
	 * 
	 */
	
	
	private Long id;
    private byte[] individual;
   // private AlgInstance algInstance;


    // Constructors

    /** default constructor */
    public BestIndividual() {
    }

    
    public BestIndividual(byte[] individual) {
        this.individual = individual;
    }
    
    
    /** full constructor 
    public BestIndividual(byte[] individual, AlgInstance algInstance) {
        this.individual = individual;
        this.algInstance = algInstance;
    }*/

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getIndividual() {
        return this.individual;
    }
    
    public void setIndividual(byte[] individual) {
        this.individual = individual;
    }
/*
    public AlgInstance getAlgInstance() {
        return this.algInstance;
    }
    
    public void setAlgInstance(AlgInstance algInstance) {
        this.algInstance = algInstance;
    }
   */








}