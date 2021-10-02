package ru.cyanide3d.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.utils.CrossoutApi;

import java.text.DecimalFormat;

public class CalcElectric extends Command {

    private final int AVG_PLAY_TIME = 11;
    private final int AVG_ELECTRIC_FROM_GAME = 20;
    private double fuelPrice = 6.2;
    private double electricPrice = 6.8;

    public CalcElectric() {
        this.name = "calce";
        this.help = "Подсчитывает количество электроники, необходимое для фарма.";
        this.arguments = "[Сумма] [Цена электроники за 10шт.]";
    }

    @Override
    protected void execute(CommandEvent event) {
        final String[] args = event.getArgs().split(" ");
        if (args.length != 1) {
            event.reply("Так не пойдет, нужно ввести кол-во монет.");
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        int sum = Integer.parseInt(args[0]);
        CrossoutApi.getElectricItem().ifPresent(item -> electricPrice = Double.parseDouble(item.getFormatSellPrice()) * 0.9);
        CrossoutApi.getFuelItem().ifPresent(item -> fuelPrice = Double.parseDouble(item.getFormatSellPrice()));

        double eSumToCanBuy = (sum / electricPrice) * 10;
        double gamesToCatchElectric = eSumToCanBuy / AVG_ELECTRIC_FROM_GAME;
        double timeToCatchElectricM = gamesToCatchElectric * AVG_PLAY_TIME;
        double timeToCatchElectricH = timeToCatchElectricM / 60;

        double fuelToCatchElectric = gamesToCatchElectric * 60;
        double fuelPriceToCatchElectric = (fuelToCatchElectric / 100) * fuelPrice;

        double extraElectricToBuyFuel = (fuelPriceToCatchElectric / electricPrice) * 10;

        event.reply("**При условии:**\n - 1 игра длится: " + AVG_PLAY_TIME + " м.\n - В среднем будет получено электроники за бой: " + AVG_ELECTRIC_FROM_GAME + " шт." +
                "\n\n**Электроники нужно для покупки:** " + Math.round(eSumToCanBuy) +
                "\n**Игр нужно сыграть игр:** " + Math.round(gamesToCatchElectric) +
                "\n**Времени займет:** " + Math.round(timeToCatchElectricM) + "м. ( " + decimalFormat.format(timeToCatchElectricH) + "ч. )" +
                "\n\n**Необходимо топлива:** " + Math.round(fuelToCatchElectric) + " л." +
                "\n**Цена топлива:** " + decimalFormat.format(fuelPriceToCatchElectric) + " монет." +
                "\n**Необходимо эл. для покупки топлива:** " + Math.round(extraElectricToBuyFuel) + " шт."
        );
    }
}
