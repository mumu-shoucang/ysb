Vue.component('wdate-input',{
	props: {
		init: {
			type: String
		},
		field: {
			type: String
		},
		initObj: {
			type: Object
		}
	},
	computed: {
		cur: function() {
			if(this.field && this.initObj) {
				return this.initObj[this.field];
			} else {
				return this.init;
			}
		}
	},
    template: '<input class="form-control form-control-sm Wdate" readOnly type="text"  v-model="cur" v-on:click="wdate" >',  /** v-bind:value="init" */
    mounted: function() {
    	var _this = this;
    	$(this.$el).on("cevent", function(event, curDate) {
			if(_this.field && _this.initObj) {
				_this.initObj[_this.field] = curDate;
			} else {
				_this.$emit('callback', curDate);
			}
    	});
    },
    methods: {
    	wdate: function(){
        	WdatePicker({
        		readOnly: true,
        		dateFmt: "yyyy-MM-dd",
        		onpicking: function(dq){
        			var cur = dq.cal.getNewDateStr();
        			$(this).trigger('cevent', cur);
        		},
        		oncleared: function() {
        			$(this).trigger('cevent', "");
        		}
        	});
        },
    }
});