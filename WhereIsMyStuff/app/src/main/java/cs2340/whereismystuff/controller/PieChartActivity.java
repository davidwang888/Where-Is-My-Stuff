package cs2340.whereismystuff.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import cs2340.whereismystuff.R;
import cs2340.whereismystuff.model.Model;

public class PieChartActivity extends AppCompatActivity {
    private PieChart _pieChart;
    private Model _model = Model.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        addData();
    }

    private void addData() {
        _pieChart = (PieChart) findViewById(R.id.piechart);
        _pieChart.setRotationEnabled(true);
        _pieChart.setDescription("");
        _pieChart.setDrawHoleEnabled(true);
        _pieChart.setTransparentCircleRadius(25f);
        _pieChart.setHoleRadius(25f);
        _pieChart.setUsePercentValues(true);


        int lostItemsSize = _model.getLostItems().size();
        int foundItemsSize = _model.getFoundItems().size();
        float total = lostItemsSize + foundItemsSize + 1f;
        float lost = lostItemsSize / total;
        float found = foundItemsSize / total;

        ArrayList<Entry> yvalues = new ArrayList<>();
        yvalues.add(new Entry(lost, 0));
        yvalues.add(new Entry(found, 1));

        PieDataSet dataSet = new PieDataSet(yvalues, "Number of Items");

        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("Lost");
        xVals.add("Found");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        _pieChart.setData(data);
        _pieChart.setCenterText("Lost vs. Found Items");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        dataSet.setColors(colors);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        Legend legend = _pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setTextSize(18f);

        _pieChart.animateXY(1400, 1400);


    }
}
