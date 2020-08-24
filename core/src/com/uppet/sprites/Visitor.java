package com.uppet.sprites;

import com.uppet.sprites.Cloud.Cloud;
import com.uppet.sprites.Cloud.CloudManager;
import com.uppet.sprites.Enemy.Enemy;
import com.uppet.sprites.Enemy.EnemyManager;
import com.uppet.sprites.MainPet.Balloon;
import com.uppet.sprites.MainPet.Pet;

public interface Visitor {
    void visit (Enemy enemy);
    void visit (EnemyManager enemyManager);
    void visit (Cloud cloud);
    void visit (CloudManager cloudManager);
    void visit (Pet pet);
    void visit (ScoreHub scoreHub);
    void visit (StartHub startHub);
    void visit (Ground ground);
}
