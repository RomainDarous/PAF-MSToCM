/*class Grid implements IDrawable {

    int rectWidth, rectHeight; // Nombre de colonnes, nombre de lignes
    Rectangle[][] rects; // ligne colonne
    /*
    [[],
    [],
    [],]
    

    Grid(int rectWidth, int rectHeight) {
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
        this.rects = new Rectangle[rectHeight][rectWidth];
        
        fillsRects();
    }


    void fillsRects() {
        
        for(int i = 0; i < rectHeight*rectWidth; i++) {
            rects[i / rectWidth][i % rectWidth] = new Rectangle(((i%rectWidth)+1) * (height/rectWidth, ((i/rectWidth)+1) * width, width/(2*rectWidth), height/(2*rectHeight)));
            
        }
    }

    void show() {
        for(int i = 0; i < rectWidth;  i++) {
            for (int j = 0; j < rectHeight; j++) {
                rects[j][i].show();
            }
        }
    }

}*/