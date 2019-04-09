public class PlateformeMobile extends Bloc{

    private int vitesse;
    private int positionInitialeX;
    private int positionInitialeY;
    private int positionFinaleX;
    private int id;
    private boolean sens = false;

    
     public PlateformeMobile(int x, int y, int id, int vitesse, int positionFinaleX){
        
        super(x,y,id, vitesse);
        this.id=id;
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=super.x;
        this.positionInitialeY=super.y;
        
    }
    
    public void tick(){
        this.deplacement();
    }

    
    private void deplacement() {
              // Plateforme mobile horizontale
        if(!sens && (id==5 || id==6 || id==7 || id==8 || id==14)){
            super.x+=vitesse;
        }
        
        if(super.x>=positionFinaleX && (id==5 || id==6 || id==7 || id==8 || id==14)){
                        sens=true;
                }

        if(sens && (id==5 || id==6 || id==7 || id==8 || id==14)){
            super.x-=vitesse;
        }
        
        if(super.x<=positionInitialeX && (id==5 || id==6 || id==7 || id==8 || id==14)){
                        sens=false;
        }
			// Plateforme mobile vertiale
        if(!sens && (id==9 || id==10 || id==11 || id==12  || id==15)){
            super.y+=vitesse;
        }
        
        if(super.y>=positionFinaleX && (id==9 || id==10 || id==11 || id==12 || id==15)){
            sens=true;
        }

        if(sens && (id==9 || id==10 || id==11 || id==12 || id==15)){
            super.y-=vitesse;
        }
        
        if(super.y<=positionInitialeY && (id==9 || id==10 || id==11 || id==12 || id==15)){
            sens=false;
        }
    }

    public int getVitesse(){
         if(!sens) return vitesse;
         if(sens) return -vitesse;
         return 0;
    }

}
