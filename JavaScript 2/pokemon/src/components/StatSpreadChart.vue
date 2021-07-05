<template>
    <canvas height="300" width="300" ref="canvas"></canvas>
</template>

<script>

//import Chart from 'chart.js';
import {
  Chart,
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip
} from 'chart.js';

Chart.register(
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip
);

export default {
    name: 'StatSpreadChart',
    props: {
        stats: {
            type: Object,
            required: true
        }
    },

    data() {
        return {
            chart: null
        }
    },

    mounted() {
        let ctx = this.$refs.canvas.getContext('2d');

        let data = {
            labels: [ 'HP', 'Attack', 'Defense', 'Speed', 'Sp. Def', 'Sp. Atk' ],
            datasets: [{
                label: 'base',
                data: [ this.stats['hp'], this.stats['attack'], this.stats['defense'], this.stats['speed'], this.stats['special-defense'], this.stats['special-attack'] ],
                fill: true,
                backgroundColor: '#3F51B580'
            }]
        }

        this.chart = new Chart(ctx, {
            type: 'radar',
            data: data,
            options: {
                responsive: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { displayColors: false },
                },

                elements: {
                    line: { borderWidth: 0 }
                },

                scales: {
                    r: {
                        angleLines: { display: true },
                        max: 200,
                        min: 0,
                        ticks: {
                            stepSize: 200,
                            callback: () => { return '' }
                        }
                    }
                }
            }
        });

        //console.log(chart.scales)

        //console.log(chart);
        //console.log(new Chart());
    }
}
</script>