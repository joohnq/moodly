import com.joohnq.mood.domain.converter.StatsRecordConverter
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlin.test.Test

class StatsRecordConverterTest {
    @Test
    fun `test toMood`() {
        val m = StatsRecordConverter.toMood(0)
        assertThat(m).isEqualTo(Mood.Depressed)
    }

    @Test
    fun `test fromMood`() {
        val m = StatsRecordConverter.fromMood(Mood.Depressed)
        assertThat(m).isEqualTo(0)
    }
}