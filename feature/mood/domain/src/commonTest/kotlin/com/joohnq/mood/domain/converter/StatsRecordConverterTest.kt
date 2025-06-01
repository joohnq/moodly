import com.joohnq.mood.domain.converter.MoodRecordConverter
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlin.test.Test

class StatsRecordConverterTest {
    @Test
    fun `test toMood`() {
        val m = MoodRecordConverter.toMood(0)
        assertThat(m).isEqualTo(Mood.Depressed)
    }

    @Test
    fun `test fromMood`() {
        val m = MoodRecordConverter.fromMood(Mood.Depressed)
        assertThat(m).isEqualTo(0)
    }
}