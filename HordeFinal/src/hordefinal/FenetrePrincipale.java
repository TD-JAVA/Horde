/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/***
 * 
 * Ceci est une interface de test afin de comprendre comment fonctionne les
 * interface graphique en Java. Il a donc fallu modifier fondamentalement le
 * code. Nous l'avions pensé de manière modulaire mais pas suffisamment pour ce type
 * d'interface. En effet, tout le déroulement du jeu n'utilise qu'un bouton.
 * Précédemment, le programme s'executait de manière à n'en jamais sortir tant
 * que ce n'est pas l'utilisateur qui demande de le quitter. Il y avait donc des
 * imbrications de fonctions. Ici, il s'agit de programmation événementielle et
 * le seul événement que nous prenons en compte est le clic sur le bouton.
 * Donc, il faut sortir des méthodes à chaque fois que le traitement est fini.
 * Et il faut également sortir des méthodes lorsqu'on a besoin des données
 * de l'utilisateur. Nous avons pensé le programme pour qu'il puisse être multilingue.
 * tous les textes du programme sont stockés dans une méthode de la classe Journal
 * Malheureusement, on ne savait gérer les fichiers au moment de ce td du coup 
 * l'ensemble des lignes de texte sont compilés dans le reste du programme dans
 * un énorme switch. Deuxième point noir, nous découvrons seulement le fonctionnement
 * des interfaces graphiques donc l'ensemble des textes propres à l'interface sont
 * en français et non modifiable. Le nom des variables est en lien avec son 
 * utilisation. Toujours pour des raisons de découverte les noms des objets graphiques
 * ne sont pas intelligent pour nous aider à mieux appréhender leur comportements.
 * 
 */

/**
 *
 * @author Gabriel,Sébastien, Valère
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale() {
        initComponents();
    }
    
//Attribut de la classe     
    private boolean partieDemarree=false; //permet d'indiquer si l'objet Jeu a été instancié.
    private boolean isNbJoueurSet=false; //permet d'indiquer si le nombre de joueur a été renseigné lors de l'initialisation.
    private boolean b=false; //booléen libre permettant de communiquer des informations entre plusieurs méthodes.
    private int choix=0; //int libre permettant de communiquer des informations entre plusieurs méthodes.
    private Jeu partie; // variable permettant de stocker la partie en cours 
    private int copieNbJoueur; //variable servant dans l'initialisation des joueurs car on appelle plusieurs fois la même fonction de manière évênementiel 
    private Menu menu; //variable permettant de stocker le menu de la partie en cours 
    ArrayList<Joueur> setTabJoueur; //tableau servant dans l'initialisation des joueurs. Il permet de stocker temporairement les joueurs avant de les stocker de la partie. 
    String strReceived=""; //Chaine récupérant les données par l'utilisateur utilisateur 
    int cpt=0; // Compteur libre permettant de communiquer des informations entre plusieurs méthodes.(il sert à voyager dans le switch principale)
/****************************Getter&Setter*************************************/
    public int getChoix() {return choix;}
    public void setChoix(int choix) {this.choix = choix;}
    public boolean isB() {return b;}
    public void setB(boolean b) {this.b = b;}
    public String getStrReceived() {return strReceived;}
    public void setStrReceived(String strReceived) {this.strReceived = strReceived;}
    public Jeu getPartie() {return partie;}
    public void setPartie(Jeu partie) {this.partie = partie;}
    public Menu getMenu() {return menu;}
    public void setMenu(Menu menu) {this.menu = menu;}
    public JButton getjButton2() {return jButton2;}
    public boolean isPartieDemarree() {return partieDemarree;}
    public void setPartieDemarree(boolean partieDemarree) {this.partieDemarree = partieDemarree;}
    public JTextField getjTextField2() {return jTextField2;}
    public void setjTextField2(JTextField jTextField2) {this.jTextField2 = jTextField2;}
    public JTextArea getjTextArea1() {return jTextArea1;}
    public void setjTextArea1(JTextArea jTextArea1) {this.jTextArea1 = jTextArea1;}
    public JButton getjButton3() {return jButton3;}
    public JPanel getjPanel3() {return jPanel3;}
    public void setjPanel3(JPanel jPanel3) {this.jPanel3 = jPanel3;}
    public JLabel getjLabel1() {return jLabel1;}
    public int getCpt() {return cpt;}
    public void setCpt(int cpt) {this.cpt = cpt;}
    public JButton getjButton4() {return jButton4;}
    public void setjButton4(JButton jButton4) {this.jButton4 = jButton4;}
    public JButton getjButton5() {return jButton5;}
    public void setjButton5(JButton jButton5) {this.jButton5 = jButton5;}
    public JButton getjButton1() {return jButton1;}
    public void setjButton1(JButton jButton1) {this.jButton1 = jButton1;}
    public JLabel getjLabel2() {return jLabel2;}
    public void setjLabel2(JLabel jLabel1) {this.jLabel2 = jLabel1;}
    public JPanel getjPanel1() {return jPanel1;}
    public void setjPanel1(JPanel jPanel1) {this.jPanel1 = jPanel1;}
    public JPanel getjPanel2() {return jPanel2;}
    public void setjPanel2(JPanel jPanel2) {this.jPanel2 = jPanel2;}
    public JTextField getjTextField1() {return jTextField1;}
    public void setjTextField1(JTextField jTextField1) {this.jTextField1 = jTextField1;}
/*****************************Fin Getter&Setter********************************/    

    
/*
    Certaines des méthodes qui suivent n'ont pas de contenu. Netbeans génére
    automatiquement du code et nous ne savons pas comment le retirer.
    */    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@S+uppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();

        jButton4.setText("jButton4");
        jButton4.setVisible(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("jButton5");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Horde");
        setPreferredSize(new java.awt.Dimension(550, 300));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });

        jButton2.setText("Suivant");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel2FocusGained(evt);
            }
        });

        jTextField1.setText("?");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setText("Suivant");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jButton1ComponentHidden(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jPanel3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel3PropertyChange(evt);
            }
        });

        jTextField2.setText("?");

        jButton3.setText("Choisir");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 126, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 126, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel1FocusGained

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed
/*
    Phase d'initialisation des joueurs.
    */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
            
        
            if(!isNbJoueurSet){
                //Si le nombre de joueur n'a pas été renseigné, on va récupérer les données saisies par l'utilisateur. on Les convertie en int.
                //On vérifie ensuite que le nombre de joueur soit différent de 0.
                //Si oui, on instancie et affecte les bons contenus aux bonnes variables.
                //On demande le nom du premier joueur. 
                partie.setNombreJoueur(Outils.conversionInt(this.getjTextField1().getText(),partie));   
                if(partie.getNombreJoueur()!=0){
                    setTabJoueur=new ArrayList<Joueur>(partie.getNombreJoueur());
                    copieNbJoueur=0;
                    isNbJoueurSet=true;
                    Outils.affichage(Journal.consulterDescription(58)+copieNbJoueur+" ?",this);
                }
                //Sinon on ne fait rien
            }else{
                //Si la variable est initialisé alors on compare notre compteur au nombre de joueur présent dans la partie.
                //On prend le nom du joueur et créer un nouveau joueur qu'on ajoute à notre tableau temporaire de joueur 
                //Après incrémentation du compteur, on compare notre compteur au nombre de joueur présent dans la partie.
                //Si il y a, on demande le nom du joueur suivant.
                
                if(copieNbJoueur<partie.getNombreJoueur()){
                    String nomJoueur=this.getjTextField1().getText();
                    Joueur unJoueur= new Joueur(this.partie,nomJoueur);
                    partie.getTabJoueur().add(unJoueur);
                    copieNbJoueur+=1;
                    if(copieNbJoueur<partie.getNombreJoueur()){
                        Outils.affichage(Journal.consulterDescription(58)+copieNbJoueur+" ?",this);
                    }else{
                        //Sinon on actualise le premier joueur et on commence l'affichage du menu du jeu. 
                        //On "efface"/cache le deuxième panel et on affiche le suivant.
                        //le bouton 4 n'apparait pas dans l'interface. Il sert à la fin de l'initialisation, le redemarrage et l'affichage du premier menu.
                        partie.setJoueurActuel(0);
                        this.jPanel2.setVisible(false);
                        Outils.affichage(partie.getMonJournal().toString(partie, 'S'),partie.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(35),partie.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(36),partie.getMonInterface());
                        this.jPanel3.setVisible(true);
                        jButton4.doClick();
                }          
            }            
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        // Lorsque la fenetre est ouverte, on souhaite la bienvenue, on cache les panel 2 et 3 et on affiche le premier. 
        this.jLabel2.setText(Journal.consulterDescription(56));
        this.jPanel1.setVisible(true);
        this.jPanel2.setVisible(false);
        this.jPanel3.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //On cache les trois panels et on commence l'initialisation, on finit par demander le nombre de joueur.   
        this.jPanel2.setVisible(false);
        this.jPanel1.setVisible(false);
        this.jPanel3.setVisible(false);
            
        if(!partieDemarree){
            partie=new Jeu();
            partie.lancerJeu(this);
            menu=partie.getMenuPartie();
        }
        
        jLabel1.setText(Journal.consulterDescription(57));
        this.jPanel2.setVisible(true);        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jPanel2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel2FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel2FocusGained

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        // Il s'agit de la grosse fonction du programme, elle contient toutes les fonctionnalités des menus du jeu.
        // On y accède par par un clique sur le bouton 3 et avec le compteur de niveau cpt.
        // Elle récupère le contenue du textfield2 et le stocke dans strReceived.
        
        strReceived=jTextField2.getText();
        jTextField2.setText("");
        
        switch(cpt){
            case 0: 
                    menu.menuNiveauZero(Outils.verification(getStrReceived(),0,partie));
                    break;
            case 1:
                        
                        menu.menuNiveauUn(Outils.verification(strReceived,0,partie));
                        break;
            //case 2:     
                        
                        //menu.interagirCase(Outils.verification(strReceived,0,partie));
              //          break;
            case 3:     
                        Outils.affichage(Journal.toString(partie,Outils.verification(strReceived,0,partie)),this);
                        cpt-=1;
                        menu.retournerMenu();
                        break;
            case 4:     
                        menu.seDeplacer(Outils.verification(strReceived,0,partie));
                        cpt-=1;
                        menu.retournerMenu();
                        break;
                        
            case 5: 
                    if(menu.accederConstruction(strReceived)){
                        Outils.affichage(Journal.consulterConstruction(partie.getMonJournal()),this);
                        Outils.affichage(Journal.consulterDescription(14),this);
                        cpt+=5;
                    }else{
                        cpt+=1;
                        menu.menuNiveauUn('I');  
                    }
                    
                    break;
            case 6:
        
                    if(!menu.getTabGrille().get(partie.getJoueurActuel().getIndiceCase()).getFouillee()||menu.getTabGrille().get(partie.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()==0||choix=='A'){    
                        switch(Outils.verifier(Outils.conversionCaractere(strReceived.charAt(0)),partie)){
                            case 'F':   Outils.affichage(Journal.consulterDescription(70),this);
                                        cpt+=13;
                                        break;
                            case 'C':
                                        Outils.affichage(Journal.consulterDescription(13),this);
                                        cpt-=1;
                                        break;
                            case 'E':
                                        Outils.afficher(7, partie);
                                        break;
                            case 'P':   Outils.afficher(6, partie);
                                        break;
                            case 'D':   menu.accederDefense();
                                        break;
                            case 'I':   b=partie.getMaVille().ouverturePorte(this.partie);
                                        cpt+=10;
                                        break;
                            case 'B':   Outils.affichage(Journal.consulterDescription(20),this);
                                        cpt+=11;
                                        break;
                            case 'M':   Outils.affichage(Journal.consulterDescription(17),this);
                                        cpt+=12;
                                        break;
                            case 'A':   menu.attaquerZombies();
                                        break;
                            case 'O':   menu.accederObjet();
                                        cpt+=15;
                                        break;
                            case 'V':   b=menu.accederVider();
                                        cpt+=17;
                                        break;
                            case 'R':   cpt-=4;
                                        menu.retournerMenu();
                                        break;
                        }
                    }else{
                        Outils.affichage(Journal.consulterDescription(7),this);
                        cpt-=5;
                        menu.menuNiveauUn('I');
                    }
                    break;
            case 7: 
                    if(b){
                        menu.prendreObjet(Outils.donnerReponseChiffre(partie.getMaVille().getEntrepot().length-1,this.partie,strReceived));
                        b=false;
                    }
                    break;
            case 8: 
                    partie.getMaVille().accederAuChantier(partie.getJoueurActuel(), partie, Outils.donnerReponseChiffre(partie.getMaVille().getBatimentEnCours().size(), partie, strReceived));
                    //Outils.affichage(Journal.consulterDescription(36), this);
                    cpt+=5;
                    break;
            case 9: 
                    b=menu.accederEntrepot(strReceived);
                    break;
            case 10:
                    partie.getMaVille().construire(partie, Outils.donnerReponseChiffre(6,partie,strReceived) );
                    break;
            case 11:
                    choix =menu.prendreObjet(Outils.donnerReponseChiffre(3, partie, strReceived));
                    break;
            case 12:
                    menu.accesObjet(Outils.conversionBoolean(strReceived, partie), choix);
                    break;
            case 13:
                    b=Outils.conversionBoolean(strReceived, partie);
                    cpt+=1;
                    break;
            case 14:
                    if(b){
                        Outils.affichage(Journal.consulterDescription(77),this);
                        b=false;
                        cpt+=1;
                    }else{
                        cpt-=13;
                        menu.menuNiveauUn('I');
                    }
                    break;
            case 15:
                    partie.getMaVille().participerAuChantier(partie, Outils.donnerReponseChiffre(partie.getJoueurActuel().getPa(), partie, strReceived), choix);
                    cpt-=14;
                    menu.menuNiveauUn('I');
                    break;
            case 16:
                    if(Outils.conversionBoolean(strReceived, partie)){
                        menu.interagirPorte(b);
                        b=false;
                    }
                    cpt-=15;
                    menu.menuNiveauUn('I');
                    break;
            case 17:
                    menu.prendreGourde(Outils.conversionBoolean(strReceived, partie));
                    cpt-=16;
                    menu.menuNiveauUn('I');
                    break;
            case 18:
                    menu.prendreRation(Outils.conversionBoolean(strReceived, partie));
                    cpt-=17;
                    menu.menuNiveauUn('I');
                    break;
            case 19:
                    menu.fouillerCase(Outils.conversionBoolean(strReceived, partie));
                    cpt-=18;
                    menu.menuNiveauUn('I');
                    break;
            case 20:
                    menu.attaquer(Outils.conversionBoolean(strReceived, partie));
                    break;
            case 21:
                    if(Outils.conversionBoolean(strReceived, partie)){
                        Outils.affichage(Journal.consulterDescription(16),this);
                        cpt+=1;
                    }else{
                        cpt-=20;
                        menu.menuNiveauUn('I');
                    }
                    break;
            case 22:
                    menu.prendreObjetCase(Outils.donnerReponseChiffre(2, partie, strReceived));
                    cpt-=21;
                    menu.menuNiveauUn('I');
                    break;
            case 23:
                    if(Outils.conversionBoolean(strReceived, partie)){
                        partie.getJoueurActuel().outilViderSac(b, choix, partie);
                    }
                    
                    cpt-=22;
                    menu.menuNiveauUn('S');
                    break;
            case 24:
                    menu.interagirSac(Outils.verification(strReceived, choix, partie));
                    break;
            case 25:
                    if(Outils.conversionBoolean(strReceived, partie)){
                         partie.getJoueurActuel().actionBoireBoisson(partie, choix);
                         menu.consommerPA();
                    }
                        cpt-=24;
                        menu.menuNiveauUn('I');
                    break;
                
            case 26:
                    menu.actionManger(Outils.conversionBoolean(strReceived, partie));
                    cpt-=25;
                    menu.menuNiveauUn('S');
                    break;    
                    
                    
            case 27:
                    menu.actionBoire(Outils.conversionBoolean(strReceived, partie));
                    cpt-=26;
                    menu.menuNiveauUn('S');
                    break;
            case 28:
                    b=Outils.conversionBoolean(strReceived, partie);
                    if(b){
                        cpt=0;
                        this.getjButton4().doClick();
                    }else{
                        cpt=0;
                        Outils.afficher(0,partie);
                    }
                    break;
            case 29:
                    choix=Outils.donnerReponseChiffre(partie.getJoueurActuel().getSac().size()-1, partie, strReceived);
                    if(choix>0&&choix<partie.getJoueurActuel().getSac().size()-1){
                        Outils.affichage(Journal.consulterDescription(29)+partie.getJoueurActuel().getSac().get(choix).getNom()+ Journal.consulterDescription(30),partie.getMonInterface());
                        cpt-=6;
                    }else{
                        Outils.afficher(4, partie);
                        cpt-=5;
                    }
                        
                    break;
        }       
    }//GEN-LAST:event_jButton3MouseClicked

    private void jPanel3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel3PropertyChange
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jPanel3PropertyChange

    private void jButton1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jButton1ComponentHidden
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ComponentHidden

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //Elle sert à la fin de l'initialisation, au redemarrage et à l'affichage du premier menu.
        if(!partie.getPartie()){
            menu.demarrer(partie, this);
        }else{
            if(b){
                this.partieDemarree=false;
                isNbJoueurSet=false;
                this.jTextArea1.setText("");
                b=false;
                jButton2.doClick();
            }else{
                Outils.afficher(0, partie);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
 
