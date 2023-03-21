import controlP5.*;

ControlP5 cp5;


// ================================ GESTION DES AGENTS ===================================
Slider maxSpeedSlider;

Slider sightAngleSlider;
CheckBox showSightAngleCheckBox;

// Force de separation 
Slider separationForceSlider;
Slider separationPerceptionRadiusSlider;

// Force de cohesion 
Slider cohesionForceSlider;
Slider cohesionPerceptionRadiusSlider;

// Force d'alignement
Slider alignForceSlider;
Slider alignPerceptionRadiusSlider;

// Errance
CheckBox wanderForceCheckBox;


// ================================ GESTION DU TERRAIN ==================================
// QuadTree
CheckBox showQuadTreeCheckBox;
Slider quadTreePerceptionRadiusSlider;


// Ajout d'elements sur le terrain
RadioButton boidObstacleRadioButton;
Slider obstacleRadiusSlider;
Slider boidNumberSlider;

// Affichage de l'enveloppe convexe
CheckBox showConvex;
CheckBox showPathPrediction;
CheckBox showBoids;

// ================================== MENU GENERAL =======================================
// Parametres
Button settingsMenuButton;
Accordion settingsMenu;

// Interruption de la simulation
Group scriptsGroup;
Button pathButton; // Trajectoire d'un groupe
Button interpolationButton; // Interpolation de la trajectoire
Button boidTimeButton; // Nombre de boids en fonction du temps
Button groupTimeButton; // Nombre de groupes en fonction du temps



// Style du texte
PFont titleFont;


void setupUI() {
    cp5 = new ControlP5(this); // Initialisation de ControlP5
    titleFont = createFont("Arial", 12);
    setupSettingsMenu();
    initDefaultValues();
    setupScriptsMenu();
}


/* Menu déroulant des scripts Python */
void setupScriptsMenu() {
    scriptsGroup = cp5.addGroup("scripts").setPosition(100, 24).setCaptionLabel("Arreter la simulation").setHeight(20).setBackgroundHeight(115).activateEvent(true).setBackgroundColor(color(138, 175, 255, 180)).setWidth(150).setOpen(false);

    scriptsGroup.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER);


    pathButton = cp5.addButton("PathButton").setCaptionLabel("Trajectoire d'un groupe").setPosition(5,5).setSize(140,20).setGroup(scriptsGroup);
    interpolationButton = cp5.addButton("InterpolationButton").setCaptionLabel("Interpolation d'une trajectoire ").setPosition(5,30).setSize(140,20).setGroup(scriptsGroup);
    boidTimeButton = cp5.addButton("BoidTimeButton").setCaptionLabel("Nombre de boids d'un groupe").setPosition(5,55).setSize(140,20).setGroup(scriptsGroup);
    groupTimeButton = cp5.addButton("GroupTimeButton").setCaptionLabel("Nombre de groupes").setPosition(5,80).setSize(140,20).setGroup(scriptsGroup);

    println("Scripts menu setup");
}


/* Menu des parametres */
void setupSettingsMenu() {
    settingsMenuButton = cp5.addButton("SettingsButton").setCaptionLabel("Parametres").setValue(0).setPosition(5,2).setSize(90, 20);
    

    // ================== MENU DEROULANT ===================
    settingsMenu = cp5.addAccordion("Settings").setPosition(5, 26).setWidth(150).addItem(setupBoidsManagerGroup()).addItem(setupTerrainManagerGroup());

    settingsMenu.setCollapseMode(Accordion.MULTI);
    settingsMenu.setVisible(showSettings);

    println("Settings menu setup"); 
}


/* Valeurs par défaut des différents éléments */
void initDefaultValues() {
    maxSpeedSlider.setValue(2);
    separationForceSlider.setValue(1);
    separationPerceptionRadiusSlider.setValue(1);
    cohesionForceSlider.setValue(1);
    cohesionPerceptionRadiusSlider.setValue(1);
    alignForceSlider.setValue(1);
    alignPerceptionRadiusSlider.setValue(1);
    sightAngleSlider.setValue(100);
    quadTreePerceptionRadiusSlider.setValue(1);
    boidObstacleRadioButton.activate(2);
    obstacleRadiusSlider.setValue(15);
    boidNumberSlider.setValue(5);

    println("Default values of slider initialized");
}



// ==================================== GESTION DU TERRAIN =====================================
Group setupTerrainManagerGroup() {
    Group terrainManagerGroup = cp5.addGroup("TerrainManagerGroup").setCaptionLabel("Gestion du terrain").setBackgroundColor(color(138, 175, 255, 180)).setHeight(20).setBackgroundHeight(260);
    terrainManagerGroup.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER);

    float y=4;
    // ============================= UI ARBRE QUATERNAIRE =================================
    cp5.addTextlabel("QuadTreeLabel")
    .setText("Quad Tree")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(terrainManagerGroup)
    ;

    y+=20;
    showQuadTreeCheckBox = cp5.addCheckBox("ShowQuadTree").setPosition(4, y).setSize(13, 13).setItemsPerRow(2).setSpacingColumn(50).addItem("QuadTree", 1).addItem("Perception", 2).moveTo(terrainManagerGroup);

    y+=20;
    quadTreePerceptionRadiusSlider = cp5.addSlider("PerceptionRadius").setCaptionLabel("Rayon de perception").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(terrainManagerGroup);
    quadTreePerceptionRadiusSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    quadTreePerceptionRadiusSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);


    // ============================= UI AJOUT D'AGENT OU OBSTACLE =============================
    y+=35;
    cp5.addTextlabel("AddElementLabel")
    .setText("Ajouter un élément")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(terrainManagerGroup)
    ;

    y+=20;
    boidObstacleRadioButton = cp5.addRadioButton("boidObstacle").setPosition(4, y).setSize(15, 15).setItemsPerRow(3).setSpacingColumn(40).addItem("Agent", 1).addItem("Obstacle", 2).addItem("Rien", 3).moveTo(terrainManagerGroup);
    
    y+=20;
    obstacleRadiusSlider = cp5.addSlider("ObstacleRadius").setCaptionLabel("Rayon de l'obstacle").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(5, 25).setNumberOfTickMarks(21).moveTo(terrainManagerGroup);

    obstacleRadiusSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    obstacleRadiusSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);

    y+=35;
    boidNumberSlider = cp5.addSlider("BoidNumber").setCaptionLabel("Nombre d'agents a ajouter").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(1, 20).setNumberOfTickMarks(20).moveTo(terrainManagerGroup);

    boidNumberSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    boidNumberSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);


    // Enveloppe convexe
    y+= 35;
    showConvex = cp5.addCheckBox("ShowConvex").setPosition(4, y).setSize(13, 13).addItem("Enveloppe convexe", 0).moveTo(terrainManagerGroup);

    // Prédiction de trajectoire
    y+=20;
    showPathPrediction = cp5.addCheckBox("ShowPathPrediction").setPosition(4, y).setSize(13, 13).addItem("Prediction de trajectoire", 0).moveTo(terrainManagerGroup);
    
    // Afficher/cacher les boids
    y+=20;
    showBoids = cp5.addCheckBox("ShowBoids").setPosition(4, y).setSize(13, 13).addItem("Afficher les agents", 0).moveTo(terrainManagerGroup).activate(0);

    
    return terrainManagerGroup;
}


// ==================================== GESTION DES AGENTS ====================================
Group setupBoidsManagerGroup() {
    Group boidsManagerGroup = cp5.addGroup("BoidsManagerGroup").setCaptionLabel("Gestion des agents").setBackgroundColor(color(138, 175, 255, 180)).setHeight(20).setBackgroundHeight(390);
    boidsManagerGroup.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER);
    
    float y = 4;
    cp5.addTextlabel("SpeedLabel")
    .setText("Speed")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(boidsManagerGroup)
    ;
    y+=20;
    maxSpeedSlider = cp5.addSlider("MaxSpeed").setCaptionLabel("Vitesse maximale").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup).setNumberOfTickMarks(6);

    maxSpeedSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    maxSpeedSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);


    // =================== UI CHAMP DE VISION DE L'AGENT ======================
    y+=35;
    cp5.addTextlabel("SightLabel")
    .setText("Visibilite")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(boidsManagerGroup)
    ;

    y+=20;
    sightAngleSlider = cp5.addSlider("SightAngle").setCaptionLabel("Visibilité").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 100).moveTo(boidsManagerGroup);

    sightAngleSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    sightAngleSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);

    y+=25;
    showSightAngleCheckBox = cp5.addCheckBox("ShowSightAngle").setPosition(4, y).setSize(13, 13).addItem("Afficher la visibilité", 0).moveTo(boidsManagerGroup);


    // ====================  UI FORCE DE SEPARATION ====================
    y+=35;
    cp5.addTextlabel("SeparationLabel")
    .setText("Séparation")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(boidsManagerGroup)
    ;

    y+=20;
    separationForceSlider = cp5.addSlider("SeparationForce").setCaptionLabel("Force de separation").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup);

    separationForceSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    separationForceSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);

    y+=25;
    separationPerceptionRadiusSlider = cp5.addSlider("PercceptionRadius").setCaptionLabel("Rayon de perception").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup);

    separationPerceptionRadiusSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    separationPerceptionRadiusSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);


    // ====================  UI FORCE DE COHESION ====================
    y+=35;
    cp5.addTextlabel("CohesionLabel")
    .setText("Cohésion")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(boidsManagerGroup)
    ;

    y+=20;
    cohesionForceSlider = cp5.addSlider("CohesionForce").setCaptionLabel("Force de cohesion").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup);

    cohesionForceSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    cohesionForceSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);

    y+=25;
    cohesionPerceptionRadiusSlider = cp5.addSlider("CohesionRadius").setCaptionLabel("Rayon de perception").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup);

    cohesionPerceptionRadiusSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    cohesionPerceptionRadiusSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);


    // ====================  UI FORCE D'ALIGNEMENT ====================
    y+=35;
    cp5.addTextlabel("AlignLabel")
    .setText("Alignement")
    .setFont(titleFont)
    .setPosition(2, y)
    .setColorValue(color(22, 34, 108))
    .moveTo(boidsManagerGroup)
    ;
    y+=20;
    alignForceSlider = cp5.addSlider("AlignForce").setCaptionLabel("Force d'alignement").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup);

    alignForceSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    alignForceSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);

    y+=25;
    alignPerceptionRadiusSlider = cp5.addSlider("AlignRadius").setCaptionLabel("Rayon de perception").setColorForeground(color(95, 211, 189)).setPosition(4, y).setSize(144, 20).setRange(0, 5).moveTo(boidsManagerGroup);

    alignPerceptionRadiusSlider.getCaptionLabel().align(ControlP5.LEFT, ControlP5.CENTER);
    alignPerceptionRadiusSlider.getValueLabel().align(ControlP5.RIGHT, ControlP5.CENTER);

    y+=25;
    wanderForceCheckBox = cp5.addCheckBox("WanderForce").setPosition(4, y).setSize(13, 13).addItem("Activer l'errance", 0).moveTo(boidsManagerGroup);

    return boidsManagerGroup;
}


/* Gere les events sur les différents composants de l'interface de contrôle */
void controlEvent(ControlEvent event) {

    // Ouverture du menu des paramètres
    if(event.isFrom("SettingsButton")) {
        if(settingsMenu != null) {
            showSettings = !showSettings;
            settingsMenu.setVisible(showSettings);
        }
        
        if(!simulationActive) {
            simulationActive = true;
            scriptsGroup.setOpen(false);
        }
    }

   if(event.isGroup() && event.getGroup().getName() == "scripts") {
        simulationActive = !simulationActive;
        if(showSettings) {
            showSettings = false;
            settingsMenu.setVisible(false);
        }
        String label = simulationActive ? "Arreter la simulation" : "Relancer la simulation";
        scriptsGroup.setCaptionLabel(label);
    }


    // Script d'affichage de trajectoire
    if(event.isFrom("PathButton")) {
        try{
                String path = sketchPath() + "\\AnalyseData.py";
                Process p = Runtime.getRuntime().exec("python " + path + " --groupid " + biggestGroupId);
                println("AnalyseData.py executed");
            }
            catch(Exception e){
                println("Error while opening AnalyseData.py");
            }
    }


    // Script d'interpolation de trajectoire
    if(event.isFrom("InterpolationButton")) {
        try{
                String path = sketchPath() + "\\Interpole.py";
                Process p = Runtime.getRuntime().exec("python " + path + " --groupid " + biggestGroupId);
                println("Interpole.py executed");
            }
            catch(Exception e){
                println("Error while opening Interpole.py");
            }
    }

    // Script pour le nombre de boids dans un groupe
    if(event.isFrom("BoidTimeButton")) {
        try{
                String path = sketchPath() + "\\BoidInGroup.py";
                Process p = Runtime.getRuntime().exec("python " + path + " --groupid " + biggestGroupId);
                println("BoidInGroup.py executed");
            }
            catch(Exception e){
                println("Error while opening BoidInGroup.py");
            }
    }

    // Script pour le nombre de groupes au cours du temps
    if(event.isFrom("GroupTimeButton")) {
        try{
                String path = sketchPath() + "\\NombreGroup.py";
                Process p = Runtime.getRuntime().exec("python " + path);
                println("NombreGroup.py executed");
            }
            catch(Exception e){
                println("Error while opening NombreGroup.py");
            }
    }

}
