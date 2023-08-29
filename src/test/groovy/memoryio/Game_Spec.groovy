package memoryio

import spock.lang.Specification

class Game_Spec extends Specification{
    def 'We can create a game-board, which has the expected dimensions.'(int width, int height){
        given: "We have a game instance."
            var main = new MainViewModel();

        when: "We tell the main ViewModel about our game-board dimensions."
            main.width.act(width);
            main.height.act(height);
        and: "We create a game based on the dimensions."
            var game = main.startGame();

        then: "The game exists."
            game.isPresent();
        and: "It has the expected state."
            game.get().width == width;
            game.get().height == height;

        where: "We used the following dimensions:"
            width | height
            2     | 3
            6     | 3
            4     | 4
            4     | 6
            8     | 6
            6     | 8
            1     | 2
    }

    def 'Certain dimensions are not permitted.'(int width, int height){
        given: "We have a game instance."
            var main = new MainViewModel();

        when: "We tell the main ViewModel about our game-board dimensions."
            main.width.act(width);
            main.height.act(height);
        and: "We try to create a game based on the dimensions."
            var game = main.startGame();

        then: "The game does not exist."
            game.isEmpty();

        where: "We used the following dimensions:"
            width | height
            1     | 1
            7     | 7
            0     | 4
            4     | 0
            -3    | 4
            2     | -3
            -2    | -5
            5     | 3
    }
}
