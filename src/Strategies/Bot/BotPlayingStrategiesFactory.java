package Strategies.Bot;

import projectenums.difficultylevel;

public class BotPlayingStrategiesFactory {
    public BotPlayingStrategies getBotPlayingStrategy(difficultylevel d)
    {
        return new EasyPlayingStategy();
    }
}
