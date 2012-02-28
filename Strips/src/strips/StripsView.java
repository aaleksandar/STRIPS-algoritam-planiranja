/*
 * StripsView.java
 */

package strips;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.util.Iterator;

import blockworld.*;
import blockworld.predicates.*;
import blockworld.actions.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import java.util.EmptyStackException;
import java.util.Vector;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;

/**
 * The application's main frame.
 */
public class StripsView extends FrameView {

    public StripsView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                //statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
//        statusAnimationLabel.setIcon(idleIcon);
//        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        //statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(true);
//                } else if ("done".equals(propertyName)) {
//                    busyIconTimer.stop();
//                    statusAnimationLabel.setIcon(idleIcon);
//                    progressBar.setVisible(false);
//                    progressBar.setValue(0);
//                } else if ("message".equals(propertyName)) {
//                    String text = (String)(evt.getNewValue());
//                    statusMessageLabel.setText((text == null) ? "" : text);
//                    messageTimer.restart();
//                } else if ("progress".equals(propertyName)) {
//                    int value = (Integer)(evt.getNewValue());
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(false);
//                    progressBar.setValue(value);
                }
            }
        });
		
		blockWorld = new BlockWorld();
		
		/*
		blockWorld.addBlock();
		blockWorld.addBlock();
		blockWorld.addBlock();
		blockWorld.addBlock();
		blockWorld.addBlock();
		blockWorld.addBlock();
		labHelp.setText(blockWorld.getAllBlocks());
		 * 
		 */
		
		
		/*
		 * primer 6
		 */
		/*
		blockWorld.currentState.add(new On('B', 'A'));
		blockWorld.currentState.add(new On('C', 'B'));
		blockWorld.currentState.add(new On('D', 'C'));
		blockWorld.currentState.add(new On('E', 'D'));
		blockWorld.currentState.add(new On('F', 'E'));
		
		blockWorld.currentState.add(new White('A'));
		blockWorld.currentState.add(new White('E'));
		blockWorld.currentState.add(new Black('C'));
		blockWorld.currentState.add(new Black('D'));
		blockWorld.currentState.add(new White('B'));
		blockWorld.currentState.add(new White('F'));
		blockWorld.currentState.add(new Clear('F'));
		blockWorld.currentState.add(new ArmEmpty());
		blockWorld.currentState.add(new OnTable('A'));
		
		
		blockWorld.desiredStack.push(new On('B', 'A'));
		blockWorld.desiredStack.push(new On('C', 'B'));
		blockWorld.desiredStack.push(new On('D', 'C'));
		blockWorld.desiredStack.push(new On('E', 'D'));
		blockWorld.desiredStack.push(new On('F', 'E'));
		
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('E'));
		blockWorld.desiredStack.push(new Black('C'));
		blockWorld.desiredStack.push(new Black('D'));
		blockWorld.desiredStack.push(new Black('B'));
		blockWorld.desiredStack.push(new White('F'));
		
		blockWorld.desiredStack.push(new Clear('F'));
		blockWorld.desiredStack.push(new ArmEmpty());
		blockWorld.desiredStack.push(new OnTable('A'));
		 * 
		 */
	
		
		/*
		 * primer 1.0
		 */
		/*
		blockWorld.currentState.add(new OnTable('A'));
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new White('A'));
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new Clear('A'));
		blockWorld.desiredStack.push(new White('A'));
		blockWorld.desiredStack.push(new Holding('A'));
		
		blockWorld.originalDesiredStack.push(new Clear('A'));
		blockWorld.originalDesiredStack.push(new White('A'));
		blockWorld.originalDesiredStack.push(new Holding('A'));
		 * 
		 */
		
		/*
		 * primer 1.1
		 */
		/*
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new Black('A'));
		blockWorld.currentState.add(new Holding('A'));
		
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new Clear('A'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new Clear('A'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		
		/*
		 * primer 2.0 
		 */
		/*
		blockWorld.currentState.add(new On('A', 'B'));
		blockWorld.currentState.add(new OnTable('B'));
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new Black('A'));
		blockWorld.currentState.add(new Black('B'));
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new On('B', 'A'));
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new Clear('B'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('B'));
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new On('B', 'A'));
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new Clear('B'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new Black('B'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		
		/*
		 * primer 2.1
		 */
		/*
		blockWorld.currentState.add(new On('A', 'B'));
		blockWorld.currentState.add(new OnTable('B'));
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new Black('A'));
		blockWorld.currentState.add(new Black('B'));
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new OnTable('B'));
		blockWorld.desiredStack.push(new Clear('A'));
		blockWorld.desiredStack.push(new Clear('B'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('B'));
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new OnTable('B'));
		blockWorld.originalDesiredStack.push(new Clear('A'));
		blockWorld.originalDesiredStack.push(new Clear('B'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new Black('B'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		/*
		 * primer 2.2
		 */
		/*
		blockWorld.currentState.add(new OnTable('A'));
		blockWorld.currentState.add(new OnTable('B'));
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new Clear('B'));
		blockWorld.currentState.add(new Black('A'));
		blockWorld.currentState.add(new White('B'));
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new OnTable('B'));
		blockWorld.desiredStack.push(new Clear('A'));
		blockWorld.desiredStack.push(new Clear('B'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('B'));
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new OnTable('B'));
		blockWorld.originalDesiredStack.push(new Clear('A'));
		blockWorld.originalDesiredStack.push(new Clear('B'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new Black('B'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		/* 
		 * primer 3.0
		 */
		/*
		blockWorld.currentState.add(new OnTable('A'));
		blockWorld.currentState.add(new On('B', 'A'));
		blockWorld.currentState.add(new On('C', 'B'));
		blockWorld.currentState.add(new White('A'));
		blockWorld.currentState.add(new Black('C'));
		blockWorld.currentState.add(new White('B'));
		blockWorld.currentState.add(new Clear('C'));
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new On('C', 'B'));
		blockWorld.desiredStack.push(new On('B', 'A'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('C'));
		blockWorld.desiredStack.push(new White('B'));
		blockWorld.desiredStack.push(new Clear('C'));
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new On('C', 'B'));
		blockWorld.originalDesiredStack.push(new On('B', 'A'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new Black('C'));
		blockWorld.originalDesiredStack.push(new White('B'));
		blockWorld.originalDesiredStack.push(new Clear('C'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		/*
		 * primer 4.0
		 */
		/*
		blockWorld.currentState.add(new OnTable('A'));
		blockWorld.currentState.add(new OnTable('B'));
		blockWorld.currentState.add(new OnTable('C'));
		blockWorld.currentState.add(new OnTable('D'));
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new Clear('B'));
		blockWorld.currentState.add(new Clear('C'));
		blockWorld.currentState.add(new Clear('D'));
		blockWorld.currentState.add(new White('A'));
		blockWorld.currentState.add(new Black('C'));
		blockWorld.currentState.add(new White('B'));
		blockWorld.currentState.add(new White('D'));		
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new On('C', 'B'));
		blockWorld.desiredStack.push(new On('B', 'A'));
		blockWorld.desiredStack.push(new OnTable('D'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('C'));
		blockWorld.desiredStack.push(new White('B'));
		blockWorld.desiredStack.push(new Black('D'));
		blockWorld.desiredStack.push(new Clear('C'));
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new On('C', 'B'));
		blockWorld.originalDesiredStack.push(new On('B', 'A'));
		blockWorld.originalDesiredStack.push(new OnTable('D'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new Black('C'));
		blockWorld.originalDesiredStack.push(new White('B'));
		blockWorld.originalDesiredStack.push(new Black('D'));
		blockWorld.originalDesiredStack.push(new Clear('C'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		/*
		 * primer 5.0
		 */
		/*
		blockWorld.currentState.add(new On('A', 'B'));
		blockWorld.currentState.add(new On('C', 'D'));
		blockWorld.currentState.add(new Clear('A'));
		blockWorld.currentState.add(new Clear('C'));
		blockWorld.currentState.add(new Clear('E'));
		blockWorld.currentState.add(new OnTable('B'));
		blockWorld.currentState.add(new OnTable('D'));
		blockWorld.currentState.add(new OnTable('E'));
		blockWorld.currentState.add(new Black('A'));
		blockWorld.currentState.add(new Black('B'));
		blockWorld.currentState.add(new White('C'));
		blockWorld.currentState.add(new White('D'));
		blockWorld.currentState.add(new White('E'));
		blockWorld.currentState.add(new ArmEmpty());
		
		blockWorld.desiredStack.push(new OnTable('A'));
		blockWorld.desiredStack.push(new OnTable('B'));
		blockWorld.desiredStack.push(new OnTable('C'));
		blockWorld.desiredStack.push(new OnTable('D'));
		blockWorld.desiredStack.push(new OnTable('E'));
		blockWorld.desiredStack.push(new Clear('A'));
		blockWorld.desiredStack.push(new Clear('B'));
		blockWorld.desiredStack.push(new Clear('C'));
		blockWorld.desiredStack.push(new Clear('D'));
		blockWorld.desiredStack.push(new Clear('E'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('B'));
		blockWorld.desiredStack.push(new Black('C'));
		blockWorld.desiredStack.push(new Black('D'));
		blockWorld.desiredStack.push(new Black('E'));		
		blockWorld.desiredStack.push(new ArmEmpty());
		
		blockWorld.originalDesiredStack.push(new OnTable('A'));
		blockWorld.originalDesiredStack.push(new OnTable('B'));
		blockWorld.originalDesiredStack.push(new OnTable('C'));
		blockWorld.originalDesiredStack.push(new OnTable('D'));
		blockWorld.originalDesiredStack.push(new OnTable('E'));
		blockWorld.originalDesiredStack.push(new Clear('A'));
		blockWorld.originalDesiredStack.push(new Clear('B'));
		blockWorld.originalDesiredStack.push(new Clear('C'));
		blockWorld.originalDesiredStack.push(new Clear('D'));
		blockWorld.originalDesiredStack.push(new Clear('E'));
		blockWorld.originalDesiredStack.push(new Black('A'));
		blockWorld.originalDesiredStack.push(new Black('B'));
		blockWorld.originalDesiredStack.push(new Black('C'));
		blockWorld.originalDesiredStack.push(new Black('D'));
		blockWorld.originalDesiredStack.push(new Black('E'));
		blockWorld.originalDesiredStack.push(new ArmEmpty());
		 * 
		 */
		
		/*
		 * primer 6.0
		 */
		/*
		blockWorld.currentState.add(new On('B', 'A'));
		blockWorld.currentState.add(new On('C', 'B'));
		blockWorld.currentState.add(new On('D', 'C'));
		blockWorld.currentState.add(new On('E', 'D'));
		blockWorld.currentState.add(new On('F', 'E'));
		blockWorld.currentState.add(new White('A'));
		blockWorld.currentState.add(new White('E'));
		blockWorld.currentState.add(new Black('C'));
		blockWorld.currentState.add(new Black('D'));
		blockWorld.currentState.add(new White('B'));
		blockWorld.currentState.add(new White('F'));
		blockWorld.currentState.add(new Clear('F'));
		blockWorld.currentState.add(new ArmEmpty());
		blockWorld.currentState.add(new OnTable('A'));
		
		blockWorld.desiredStack.add(new On('F', 'E'));
		blockWorld.desiredStack.add(new On('E', 'D'));
		blockWorld.desiredStack.add(new On('D', 'C'));
		blockWorld.desiredStack.add(new On('C', 'B'));
		blockWorld.desiredStack.add(new On('B', 'A'));
		blockWorld.desiredStack.push(new Black('A'));
		blockWorld.desiredStack.push(new Black('E'));
		blockWorld.desiredStack.push(new Black('C'));
		blockWorld.desiredStack.push(new Black('D'));
		blockWorld.desiredStack.push(new Black('B'));
		blockWorld.desiredStack.push(new White('F'));
		blockWorld.desiredStack.push(new Clear('F'));
		blockWorld.desiredStack.push(new ArmEmpty());
		blockWorld.desiredStack.push(new OnTable('A'));
		 * 
		 */
		
		this.getFrame().setTitle("STRIPS algorithm"); 
		
		blocksArray[0] = labBlockA;
		blocksArray[1] = labBlockB;
		blocksArray[2] = labBlockC;
		blocksArray[3] = labBlockD;
		blocksArray[4] = labBlockE;
		blocksArray[5] = labBlockF;
		blocksArray[6] = labBlockG;
		blocksArray[7] = labBlockH;
		
		addBlock();
		
		jLabel3.setEnabled(false);
		jLabel4.setEnabled(false);
		cbBlockX.setEnabled(false);
		cbBlockY.setEnabled(false);
		
		enabledBlocksNumber = 1;
		
		animationTimer = new Timer(800, new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				simulationStep();
			}
		});
		
		redraw();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = StripsApp.getApplication().getMainFrame();
            aboutBox = new StripsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        StripsApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        labInitial = new javax.swing.JLabel();
        layPanelInitialState = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPredicates = new javax.swing.JList();
        cbBlockX = new javax.swing.JComboBox();
        cbBlockY = new javax.swing.JComboBox();
        btnAddPredicate = new javax.swing.JButton();
        toggleBtn = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAddObject = new javax.swing.JButton();
        btnRemoveObject = new javax.swing.JButton();
        labHelp = new javax.swing.JLabel();
        btnSimulation = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textareaActions = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textareaCurrentPosition = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textareaDesiredStack = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        labBlockA = new javax.swing.JLabel();
        labBlockB = new javax.swing.JLabel();
        labBlockC = new javax.swing.JLabel();
        labBlockD = new javax.swing.JLabel();
        labBlockE = new javax.swing.JLabel();
        labBlockF = new javax.swing.JLabel();
        labBlockG = new javax.swing.JLabel();
        labBlockH = new javax.swing.JLabel();
        btnToggleAnimation = new javax.swing.JToggleButton();

        mainPanel.setAutoscrolls(true);
        mainPanel.setMinimumSize(new java.awt.Dimension(750, 500));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(750, 500));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(strips.StripsApp.class).getContext().getResourceMap(StripsView.class);
        labInitial.setFont(resourceMap.getFont("labHelp.font")); // NOI18N
        labInitial.setText(resourceMap.getString("labInitial.text")); // NOI18N
        labInitial.setName("labInitial"); // NOI18N
        mainPanel.add(labInitial, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 38, 157, 27));

        layPanelInitialState.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        layPanelInitialState.setName("layPanelInitialState"); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listPredicates.setFont(resourceMap.getFont("listPredicates.font")); // NOI18N
        listPredicates.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "On(x, y)", "OnTable(x)", "Clear(x)", "Holding(x)", "ArmEmpty", "Black(x)", "White(x)" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listPredicates.setName("listPredicates"); // NOI18N
        listPredicates.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPredicatesMouseClicked(evt);
            }
        });
        listPredicates.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                listPredicatesPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(listPredicates);

        jScrollPane1.setBounds(10, 10, 90, 120);
        layPanelInitialState.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cbBlockX.setName("cbBlockX"); // NOI18N
        cbBlockX.setPreferredSize(new java.awt.Dimension(48, 24));
        cbBlockX.setBounds(140, 10, 50, 24);
        layPanelInitialState.add(cbBlockX, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cbBlockY.setMinimumSize(new java.awt.Dimension(48, 24));
        cbBlockY.setName("cbBlockY"); // NOI18N
        cbBlockY.setBounds(140, 40, 50, 24);
        layPanelInitialState.add(cbBlockY, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(strips.StripsApp.class).getContext().getActionMap(StripsView.class, this);
        btnAddPredicate.setAction(actionMap.get("addPredicate")); // NOI18N
        btnAddPredicate.setFont(resourceMap.getFont("btnAddPredicate.font")); // NOI18N
        btnAddPredicate.setText(resourceMap.getString("btnAddPredicate.text")); // NOI18N
        btnAddPredicate.setName("btnAddPredicate"); // NOI18N
        btnAddPredicate.setBounds(110, 80, 130, 50);
        layPanelInitialState.add(btnAddPredicate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        toggleBtn.setAction(actionMap.get("toggleInitDesired")); // NOI18N
        toggleBtn.setFont(resourceMap.getFont("toggleBtn.font")); // NOI18N
        toggleBtn.setText(resourceMap.getString("toggleBtn.text")); // NOI18N
        toggleBtn.setName("toggleBtn"); // NOI18N
        toggleBtn.setBounds(10, 140, 230, 30);
        layPanelInitialState.add(toggleBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(120, 10, 20, 15);
        layPanelInitialState.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(120, 40, 20, 15);
        layPanelInitialState.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mainPanel.add(layPanelInitialState, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 344, 253, 177));

        btnAddObject.setAction(actionMap.get("addBlock")); // NOI18N
        btnAddObject.setFont(resourceMap.getFont("toggleBtn.font")); // NOI18N
        btnAddObject.setText(resourceMap.getString("btnAddObject.text")); // NOI18N
        btnAddObject.setName("btnAddObject"); // NOI18N
        mainPanel.add(btnAddObject, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 130, -1));

        btnRemoveObject.setAction(actionMap.get("removeBlock")); // NOI18N
        btnRemoveObject.setFont(resourceMap.getFont("toggleBtn.font")); // NOI18N
        btnRemoveObject.setText(resourceMap.getString("btnRemoveObject.text")); // NOI18N
        btnRemoveObject.setEnabled(false);
        btnRemoveObject.setName("btnRemoveObject"); // NOI18N
        mainPanel.add(btnRemoveObject, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 130, -1));

        labHelp.setFont(resourceMap.getFont("labHelp.font")); // NOI18N
        labHelp.setText(resourceMap.getString("labHelp.text")); // NOI18N
        labHelp.setName("labHelp"); // NOI18N
        mainPanel.add(labHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        btnSimulation.setAction(actionMap.get("simulationStep")); // NOI18N
        btnSimulation.setFont(resourceMap.getFont("toggleBtn.font")); // NOI18N
        btnSimulation.setText(resourceMap.getString("btnSimulation.text")); // NOI18N
        btnSimulation.setName("btnSimulation"); // NOI18N
        mainPanel.add(btnSimulation, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, -1, 53));

        btnReset.setAction(actionMap.get("resetWorld")); // NOI18N
        btnReset.setFont(resourceMap.getFont("toggleBtn.font")); // NOI18N
        btnReset.setText(resourceMap.getString("btnReset.text")); // NOI18N
        btnReset.setName("btnReset"); // NOI18N
        mainPanel.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 90, -1));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        textareaActions.setColumns(13);
        textareaActions.setFont(resourceMap.getFont("textareaActions.font")); // NOI18N
        textareaActions.setRows(5);
        textareaActions.setName("textareaActions"); // NOI18N
        jScrollPane2.setViewportView(textareaActions);

        mainPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 71, 160, 200));

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        textareaCurrentPosition.setColumns(13);
        textareaCurrentPosition.setFont(resourceMap.getFont("textareaActions.font")); // NOI18N
        textareaCurrentPosition.setRows(5);
        textareaCurrentPosition.setMinimumSize(new java.awt.Dimension(0, 10));
        textareaCurrentPosition.setName("textareaCurrentPosition"); // NOI18N
        jScrollPane3.setViewportView(textareaCurrentPosition);

        mainPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 71, 157, 237));

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setFont(resourceMap.getFont("labHelp.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        mainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 44, -1, -1));

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        textareaDesiredStack.setColumns(13);
        textareaDesiredStack.setFont(resourceMap.getFont("textareaActions.font")); // NOI18N
        textareaDesiredStack.setRows(5);
        textareaDesiredStack.setName("textareaDesiredStack"); // NOI18N
        jScrollPane4.setViewportView(textareaDesiredStack);

        mainPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 71, 202, 201));

        jLabel2.setFont(resourceMap.getFont("labHelp.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        mainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 44, -1, -1));

        labBlockA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockA.setText(resourceMap.getString("labBlockA.text")); // NOI18N
        labBlockA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockA.setDoubleBuffered(true);
        labBlockA.setEnabled(false);
        labBlockA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labBlockA.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockA.setName("labBlockA"); // NOI18N
        labBlockA.setOpaque(true);
        mainPanel.add(labBlockA, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 50, 30));

        labBlockB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockB.setText(resourceMap.getString("labBlockB.text")); // NOI18N
        labBlockB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockB.setDoubleBuffered(true);
        labBlockB.setEnabled(false);
        labBlockB.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockB.setName("labBlockB"); // NOI18N
        labBlockB.setOpaque(true);
        mainPanel.add(labBlockB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, 50, 30));

        labBlockC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockC.setText(resourceMap.getString("labBlockC.text")); // NOI18N
        labBlockC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockC.setDoubleBuffered(true);
        labBlockC.setEnabled(false);
        labBlockC.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockC.setName("labBlockC"); // NOI18N
        labBlockC.setOpaque(true);
        mainPanel.add(labBlockC, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 50, 30));

        labBlockD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockD.setText(resourceMap.getString("labBlockD.text")); // NOI18N
        labBlockD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockD.setDoubleBuffered(true);
        labBlockD.setEnabled(false);
        labBlockD.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockD.setName("labBlockD"); // NOI18N
        labBlockD.setOpaque(true);
        mainPanel.add(labBlockD, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 50, 30));

        labBlockE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockE.setText(resourceMap.getString("labBlockE.text")); // NOI18N
        labBlockE.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockE.setDoubleBuffered(true);
        labBlockE.setEnabled(false);
        labBlockE.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockE.setName("labBlockE"); // NOI18N
        labBlockE.setOpaque(true);
        mainPanel.add(labBlockE, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 490, 50, 30));

        labBlockF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockF.setText(resourceMap.getString("labBlockF.text")); // NOI18N
        labBlockF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockF.setDoubleBuffered(true);
        labBlockF.setEnabled(false);
        labBlockF.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockF.setName("labBlockF"); // NOI18N
        labBlockF.setOpaque(true);
        mainPanel.add(labBlockF, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 490, 50, 30));

        labBlockG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockG.setText(resourceMap.getString("labBlockG.text")); // NOI18N
        labBlockG.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockG.setDoubleBuffered(true);
        labBlockG.setEnabled(false);
        labBlockG.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockG.setName("labBlockG"); // NOI18N
        labBlockG.setOpaque(true);
        mainPanel.add(labBlockG, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 50, 30));

        labBlockH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labBlockH.setText(resourceMap.getString("labBlockH.text")); // NOI18N
        labBlockH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        labBlockH.setDoubleBuffered(true);
        labBlockH.setEnabled(false);
        labBlockH.setMaximumSize(new java.awt.Dimension(50, 50));
        labBlockH.setName("labBlockH"); // NOI18N
        labBlockH.setOpaque(true);
        mainPanel.add(labBlockH, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 50, 30));

        btnToggleAnimation.setAction(actionMap.get("toggleAnimation")); // NOI18N
        btnToggleAnimation.setText(resourceMap.getString("btnToggleAnimation.text")); // NOI18N
        btnToggleAnimation.setName("btnToggleAnimation"); // NOI18N
        mainPanel.add(btnToggleAnimation, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, -1));

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

	private void listPredicatesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_listPredicatesPropertyChange
		
		
			
	}//GEN-LAST:event_listPredicatesPropertyChange

	public void setEnableButtonListBoxes() {
		if (listPredicates.getSelectedIndex() == 0) {
			jLabel3.setEnabled(true);
			jLabel4.setEnabled(true);
			cbBlockX.setEnabled(true);
			cbBlockY.setEnabled(true);
		}
		else if (listPredicates.getSelectedIndex() == 4) {
			jLabel3.setEnabled(false);
			jLabel4.setEnabled(false);
			cbBlockX.setEnabled(false);
			cbBlockY.setEnabled(false);
		}
		else {
			jLabel3.setEnabled(true);
			jLabel4.setEnabled(false);
			cbBlockX.setEnabled(true);
			cbBlockY.setEnabled(false);
		}
	}
	
	private void listPredicatesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPredicatesMouseClicked
		setEnableButtonListBoxes();
	}//GEN-LAST:event_listPredicatesMouseClicked

	@Action
	public void addBlock() {
		if (enabledBlocksNumber < 8) {
			blockWorld.addBlock();
//			labHelp.setText(blockWorld.getAllBlocks());
			cbBlockX.setModel(blockWorld.getComboBoxModel());
			cbBlockY.setModel(blockWorld.getComboBoxModel());

			blocksArray[enabledBlocksNumber].setEnabled(true);
			enabledBlocksNumber++;
		}
	}

	@Action
	public void removeBlock() {
		blockWorld.removeBlock();
		labHelp.setText(blockWorld.getAllBlocks());
		cbBlockX.setModel(blockWorld.getComboBoxModel());
		cbBlockY.setModel(blockWorld.getComboBoxModel());
	}

	@Action
	public void addPredicate() {
		if (listPredicates.isSelectionEmpty()) labHelp.setText("Nije selektovan nijedan predikat!");
		else {
			char c1 = cbBlockX.getSelectedItem().toString().charAt(0);
			char c2 = cbBlockY.getSelectedItem().toString().charAt(0);
			
			Predicate pre = null;
			
			switch (listPredicates.getAnchorSelectionIndex()) {
				case 0: { // On
					if (c1 == c2) {
						labHelp.setText("error: isti blokovi!!");
						return;
					}
					pre = new On(c1, c2);
					break;
				}
				case 1: { // OnTable
					pre = new OnTable(c1);
					break;
				}
				case 2: { // Clear
					pre = new Clear(c1);
					break;
				}
				case 3: { // Holding
					pre = new Holding(c1);
					break;
				}
				case 4: { // ArmEmpty
					pre = new ArmEmpty();
					break;
				}
				case 5: { // Black
					pre = new Black(c1);
					break;
				}
				case 6: { // White
					pre = new White(c1);
					break;
				}
				default: break;
			}
			
			if (pre != null) {
				if (toggleBtn.isSelected() == false) {
					blockWorld.addState(pre);
				}
				else {
					boolean justPush = false;
					
					if (pre.getName().equals("On")) {
						Iterator<Stackable> itr = blockWorld.desiredStack.iterator();
						justPush = true;
						
						int putOnPosition = 0;
					
						while (itr.hasNext()) {
							Stackable skb = itr.next();
							if (skb.getName().equals("On") && skb.getX() == pre.getY()) {
								putOnPosition = blockWorld.desiredStack.indexOf(skb);
								break;
							}
						}
						
						Iterator<Stackable> itr2 = blockWorld.desiredStack.iterator();
						while (itr2.hasNext()) {
							Stackable skb = itr2.next();
							if (skb.getName().equals("On") && skb.getY() == pre.getX()) {
								putOnPosition = blockWorld.desiredStack.indexOf(skb) + 1;
								break;
							}
						}
						
						blockWorld.desiredStack.add(putOnPosition, pre);
						justPush = false;
					}
					else if (pre.getName().equals("OnTable")){
						Iterator<Stackable> itr = blockWorld.desiredStack.iterator();
						int lastOn = -1;
						while (itr.hasNext()) {
							Stackable skb = itr.next();
							if (skb.getName().equals("On")) lastOn = blockWorld.desiredStack.indexOf(skb);
						}
						blockWorld.desiredStack.add(lastOn+1, pre);
					}
					else blockWorld.desiredStack.push(pre); 
					
//					if (justPush) blockWorld.desiredStack.push(pre);
					
					blockWorld.originalDesiredStack.push(pre);
					/*
					Iterator<Stackable> itr = blockWorld.desiredStack.iterator();
					while (itr.hasNext()) {
						Stackable skb = itr.next();
						if (skb.getName().equals("On") && skb.getX() ==)
					}
					
					int i = blockWorld.desiredStack.indexOf(pre);
					if (i < 0) blockWorld.desiredStack.push(pre);
					else blockWorld.desiredStack.add(i, pre);
					
					blockWorld.originalDesiredStack.push(pre);
					 * 
					 */
				}
				
			}
		}
		redraw();
		
	}
	
	private void redraw() {
		textareaCurrentPosition.setText(blockWorld.getInitialContent());
		textareaDesiredStack.setText(blockWorld.getDesiredContent());
		textareaActions.setText(blockWorld.getActions());
		
		if (doNotRedraw == true) {
			doNotRedraw = false;
		}
		else redrawBlocks();
	}

	@Action
	public void toggleInitDesired() {
//		if (toggleBtn.isSelected() == true) toggleBtn.setText("Desired State Mode");
//		else toggleBtn.setText("Initial State Mode");
	}

	@Action
	public void simulationStep() {
		btnToggleAnimation.setSelected(false);
		Stackable peek = null;
		
		try { peek = blockWorld.desiredStack.peek(); }
		catch (EmptyStackException e) {
			labHelp.setText("DONE!");
			animationTimer.stop();
			btnToggleAnimation.setSelected(false);
			return;
		}
		
		//char c1 = peek.getX();
		//char c2 = peek.getY();

		if (peek.getType().equals("Predicate")) {
			
			if (blockWorld.isInCurrentState((Predicate)peek)) {
				blockWorld.desiredStack.pop();
			}
			else if (blockWorld.findSolution((Predicate)peek)) {
//				labHelp.setText("YEY SOLUTION!");
			}
//			else labHelp.setText("NO SOLUTION! :(");
			
		} // ^predicate
		else if (peek.getType().equals("Action")) {
			Stackable a = (Stackable)blockWorld.desiredStack.pop();
			
			// glup sam i ne znam da pozovem metodu apstraktne klase! ovo ce raditi though...
			if (a.getName().equals("Pickup")) {
				Pickup p = (Pickup)a;
				p.applyAction(blockWorld);
			}
			else if (a.getName().equals("Putdown")) {
				Putdown p = (Putdown)a;
				p.applyAction(blockWorld);
			}
			else if (a.getName().equals("Unstack")) {
				Unstack p = (Unstack)a;
				p.applyAction(blockWorld);
			}
			else if (a.getName().equals("Stack")) {
				Stack p = (Stack)a;
				p.applyAction(blockWorld);
			}
			else if (a.getName().equals("Assimilate")) {
				Assimilate p = (Assimilate)a;
				p.applyAction(blockWorld);
			}
			
			
			//
		} // ^action
		else if (peek.getType().equals("PredicateVect")) {
			//labHelp.setText("predikat samo pop-ovan.. TOFIX");
			
			PredicateVect pv = (PredicateVect)peek;
			
			Iterator<Predicate> itr = pv.vect.iterator();
			while (itr.hasNext()) {
				Predicate p = itr.next();
				
				if (blockWorld.isInCurrentState(p) == false) {
					blockWorld.findSolution(p); 
				}
				
				// NEW FIX;
			}
			
			blockWorld.desiredStack.pop();
		}
		else labHelp.setText("ERROR!");
			
		redraw();
	}
	
	public int getArrayPosition(char c) {
		switch (c) {
			case 'A': { return 0; }
			case 'B': { return 1; }
			case 'C': { return 2; }
			case 'D': { return 3; }
			case 'E': { return 4; }
			case 'F': { return 5; }
			case 'G': { return 6; }
			case 'H': { return 7; }
			default: return -1;
		}
	}
	
	public void redrawBlocks() {
		Vector<Character> stackable = new Vector<Character>();
		
		// initial iterator
		Iterator<Predicate> itr1 = blockWorld.currentState.iterator();
		while (itr1.hasNext()) {
			Predicate p = itr1.next();
			
			char symbol = p.getX();
			int i = getArrayPosition(symbol);
			
			if (p.getName().equals("White")) {
				Color bc = new Color(238, 238, 238);
				Color fc = new Color(51, 51, 51);
				
				blocksArray[i].setBackground(bc);
				blocksArray[i].setForeground(fc);
			}
			else if (p.getName().equals("Black")) {
				Color bc = new Color(51, 51, 51);
				Color fc = new Color(238, 238, 238);
				
				blocksArray[i].setBackground(bc);
				blocksArray[i].setForeground(fc);
			}
			else if (p.getName().equals("OnTable")) {
				blocksArray[i].setBounds(280 + i*60, 490, blocksArray[i].getWidth(), blocksArray[i].getHeight());
				stackable.add(symbol);
			}
			else if (p.getName().equals("Holding")) {
				blocksArray[i].setBounds(
						200,
						300, 
						blocksArray[i].getWidth(), 
						blocksArray[i].getHeight()
						);
			}
		}
				
		// second pass (dependable predicates)
		while (stackable.isEmpty() == false) {
			char symbol = stackable.get(0);
			
			boolean preventLoop = true;
			
			Iterator<Predicate> itr2 = blockWorld.currentState.iterator();
			while (itr2.hasNext()) {
				Predicate iterirajuciPredikat = itr2.next();
			
				char symbolX = iterirajuciPredikat.getX();
				char symbolY = iterirajuciPredikat.getY();
				int x = getArrayPosition(symbolX);
				int y = getArrayPosition(symbolY);
				
				if (iterirajuciPredikat.getName().equals("Clear")) {
					if (symbolX == symbol) {
						stackable.remove(0);
						preventLoop = false;
					}
				}
				else if (iterirajuciPredikat.getName().equals("On")) {			
					if (symbolY == symbol) {
						int bottom = getArrayPosition(symbol);
						int top = getArrayPosition(symbolX);
						
						// ne iscrtava prvi put... ?
						blocksArray[top].setBounds(
								blocksArray[bottom].getX(), 
								blocksArray[bottom].getY() - 30, 
								blocksArray[top].getWidth(), 
								blocksArray[top].getHeight()
								);
						
						stackable.remove(0);
						stackable.add(symbolX);
						preventLoop = false;
					}	
				}
			}
			if (preventLoop) stackable.remove(0); // u slucaju dodavanja nece biti izbacivanja, pa se ovde preventira loop
		}
	}

	@Action
	public void resetWorld() {
//		blockWorld.reset();
		blockWorld = new BlockWorld();
		
		
		jLabel3.setEnabled(false);
		jLabel4.setEnabled(false);
		
		setEnableButtonListBoxes();
		
		cbBlockX.setModel(blockWorld.getComboBoxModel());
		cbBlockY.setModel(blockWorld.getComboBoxModel());
		
		
		enabledBlocksNumber = 1;
		Block.c = 'A';
		addBlock();
		
		Color bc = new Color(238, 238, 238);
		Color fc = new Color(51, 51, 51);
		
		for (int i=0; i<8; i++) {
			if (i == 0) blocksArray[i].setEnabled(true);
			else blocksArray[i].setEnabled(false);
			blocksArray[i].setBackground(bc);
			blocksArray[i].setForeground(fc);
		}
		doNotRedraw = true;
		labHelp.setText(null);
		
		
		animationTimer.stop();
		btnToggleAnimation.setSelected(false);
		toggleBtn.setSelected(false);
		
		
		redraw();
	}

	@Action
	public void toggleAnimation() {
		if (btnToggleAnimation.isSelected() == true) {
			animationTimer.start();
		}
		else {
			animationTimer.stop();
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddObject;
    private javax.swing.JButton btnAddPredicate;
    private javax.swing.JButton btnRemoveObject;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimulation;
    private javax.swing.JToggleButton btnToggleAnimation;
    private javax.swing.JComboBox cbBlockX;
    private javax.swing.JComboBox cbBlockY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labBlockA;
    private javax.swing.JLabel labBlockB;
    private javax.swing.JLabel labBlockC;
    private javax.swing.JLabel labBlockD;
    private javax.swing.JLabel labBlockE;
    private javax.swing.JLabel labBlockF;
    private javax.swing.JLabel labBlockG;
    private javax.swing.JLabel labBlockH;
    private javax.swing.JLabel labHelp;
    private javax.swing.JLabel labInitial;
    private javax.swing.JLayeredPane layPanelInitialState;
    private javax.swing.JList listPredicates;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea textareaActions;
    private javax.swing.JTextArea textareaCurrentPosition;
    private javax.swing.JTextArea textareaDesiredStack;
    private javax.swing.JToggleButton toggleBtn;
    // End of variables declaration//GEN-END:variables

	private BlockWorld blockWorld;
	
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
	
	private JLabel [] blocksArray = new JLabel[8];
	private int enabledBlocksNumber;
	
	private final Timer animationTimer;
	
	private boolean doNotRedraw;
}
